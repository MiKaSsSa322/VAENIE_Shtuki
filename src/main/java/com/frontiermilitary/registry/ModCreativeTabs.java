package com.frontiermilitary.registry;

import com.frontiermilitary.FrontierMilitary;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(
                    Registries.CREATIVE_MODE_TAB,
                    FrontierMilitary.MODID
            );

    public static final RegistryObject<CreativeModeTab> MAIN =
            TABS.register("main",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.frontiermilitary"))
                            .icon(() -> ModItems.PULSE_SIDEARM.get().getDefaultInstance())
                            .displayItems((parameters, output) -> {
                                output.accept(ModItems.PULSE_SIDEARM.get());
                                output.accept(ModItems.RIFLE_AMMO.get());
                                output.accept(ModItems.FIELD_RATION.get());
                                output.accept(ModItems.REINFORCED_FIBER.get());

                                output.accept(ModBlocks.METAL_FENCE.get());
                                output.accept(ModBlocks.SECURITY_BARS.get());
                                output.accept(ModBlocks.CONCRETE_BARRIER.get());
                                output.accept(ModBlocks.WATCH_POST_LAMP.get());
                            })
                            .build()
            );

    private ModCreativeTabs() {}
}
