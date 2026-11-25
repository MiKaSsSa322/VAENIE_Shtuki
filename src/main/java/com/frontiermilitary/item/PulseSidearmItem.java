package com.frontiermilitary.item;

import com.frontiermilitary.registry.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.TooltipFlag;
import java.util.List;

public class PulseSidearmItem extends Item {
    private static final String AMMO_TAG = "FMAmmo";
    private static final int MAG_SIZE = 20; // simplified in-game capacity
    public PulseSidearmItem(Properties properties) { super(properties); }

    @Override public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isShiftKeyDown()) {
            if (!level.isClientSide) reload(player, stack);
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }
        if (!level.isClientSide) {
            int loaded = stack.getOrCreateTag().getInt(AMMO_TAG);
            if (loaded <= 0) {
                level.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 0.35F, 0.9F);
                player.getCooldowns().addCooldown(this, 5);
                return InteractionResultHolder.consume(stack);
            }
            if (level instanceof ServerLevel server) {
                Vec3 look = player.getLookAngle();
                SmallFireball projectile = new SmallFireball(server, player, look.x, look.y, look.z);
                Vec3 eye = player.getEyePosition();
                projectile.setPos(eye.x + look.x * 0.8, eye.y + look.y * 0.8, eye.z + look.z * 0.8);
                server.addFreshEntity(projectile);
            }
            stack.getOrCreateTag().putInt(AMMO_TAG, loaded - 1);
            level.playSound(null, player.blockPosition(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 0.45F, 1.05F);
            player.getCooldowns().addCooldown(this, 8);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    private void reload(Player player, ItemStack weapon) {
        int loaded = weapon.getOrCreateTag().getInt(AMMO_TAG);
        if (loaded >= MAG_SIZE) return;
        for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++) {
            ItemStack supply = player.getInventory().getItem(slot);
            if (supply.is(ModItems.RIFLE_AMMO.get())) {
                supply.shrink(1);
                weapon.getOrCreateTag().putInt(AMMO_TAG, MAG_SIZE);
                player.getCooldowns().addCooldown(this, 16);
                player.level().playSound(null, player.blockPosition(), SoundEvents.IRON_DOOR_CLOSE, SoundSource.PLAYERS, 0.35F, 1.35F);
                return;
            }
        }
        player.level().playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 0.3F, 1.0F);
    }

    @Override public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.frontiermilitary.rifle_ammo_count", stack.getOrCreateTag().getInt(AMMO_TAG), MAG_SIZE));
        tooltip.add(Component.translatable("tooltip.frontiermilitary.reload_hint"));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
