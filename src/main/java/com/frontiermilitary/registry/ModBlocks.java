package com.frontiermilitary.registry;

import com.frontiermilitary.FrontierMilitary;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FrontierMilitary.MODID);

    public static final RegistryObject<Block> METAL_FENCE = register("metal_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(5.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SECURITY_BARS = register("security_bars",
            () -> new IronBarsBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(5.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CONCRETE_BARRIER = register("concrete_barrier",
            () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY)
                    .strength(8.0F, 12.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WATCH_POST_LAMP = register("watch_post_lamp",
            () -> new LanternBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(3.5F).sound(SoundType.LANTERN).lightLevel(state -> 15)
                    .noOcclusion().requiresCorrectToolForDrops()));

    private static RegistryObject<Block> register(String name, Supplier<Block> block) {
        RegistryObject<Block> object = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(object.get(), new Item.Properties()));
        return object;
    }

    private ModBlocks() {}
}
