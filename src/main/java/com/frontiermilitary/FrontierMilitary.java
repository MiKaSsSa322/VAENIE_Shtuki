package com.frontiermilitary;

import com.frontiermilitary.registry.ModBlocks;
import com.frontiermilitary.registry.ModItems;
import com.frontiermilitary.registry.ModCreativeTabs;
import com.frontiermilitary.config.FMConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FrontierMilitary.MODID)
public class FrontierMilitary {
    public static final String MODID = "frontiermilitary";

    public FrontierMilitary() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModCreativeTabs.TABS.register(bus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FMConfig.SPEC);
    }
}
