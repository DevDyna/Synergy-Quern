package com.synergy.quern;

import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zItems;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredRegister.Items;

public class CreativeTab {
    @SubscribeEvent
    public static void register(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS)
            event.accept(zBlocks.QUERN.get());

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
            accept(event,
                    zItems.zSimple,
                    zItems.zDusts,
                    zItems.zGears);

    }

    public static void accept(BuildCreativeModeTabContentsEvent e, DeferredRegister.Items... items) {
        for (Items i : items)
            for (DeferredHolder<Item, ? extends Item> r : i.getEntries())
                e.accept(r.get());
    }
}