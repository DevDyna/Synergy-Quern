package com.synergy.quern.common;

import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zItems;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class CreativeTabs {

    @SubscribeEvent
    public static void register(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS)
            event.accept(zBlocks.QUERN.get());

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
            zItems.zItem.getEntries().forEach(i -> event.accept(i.get()));

    }
}
