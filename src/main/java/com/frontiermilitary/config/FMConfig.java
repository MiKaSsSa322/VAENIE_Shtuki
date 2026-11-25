package com.frontiermilitary.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class FMConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.IntValue SMALL_CAMP_SPACING;
    public static final ForgeConfigSpec.IntValue LARGE_BASE_SPACING;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("world_generation");
        SMALL_CAMP_SPACING = builder
                .comment("Reserved for future camp structure placement; lower values mean more frequent camps.")
                .defineInRange("smallCampSpacing", 24, 8, 128);
        LARGE_BASE_SPACING = builder
                .comment("Reserved for future base structure placement; higher values mean rarer bases.")
                .defineInRange("largeBaseSpacing", 96, 32, 512);
        builder.pop();
        SPEC = builder.build();
    }

    private FMConfig() {}
}
