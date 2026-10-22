package com.synergy.quern;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.*;

public class Config {

        public static BooleanValue DISABLE_ITEM_RENDER;
        public static DoubleValue MAX_QUERN_SPEED;
        public static DoubleValue QUERN_SPEED_INCREASER;

        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        public static void register(ModContainer c) {

                // BUILDER.comment("Rendering").push("rendering");

                MAX_QUERN_SPEED = BUILDER
                                .comment("Max Quern Speed")
                                .defineInRange("max_speed", 10f, 1, Integer.MAX_VALUE);

                QUERN_SPEED_INCREASER = BUILDER
                                .comment("Quern Speed Increaser")
                                .defineInRange("speed_increaser", 0.1d, 0.01d, Integer.MAX_VALUE);

                DISABLE_ITEM_RENDER = BUILDER
                                .comment("Disable Item Renderer")
                                .define("disable_item_render", false);

                // BUILDER.pop();

                c.registerConfig(ModConfig.Type.SERVER, BUILDER.build());
        }

}