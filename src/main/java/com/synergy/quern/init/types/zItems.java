package com.synergy.quern.init.types;

import static com.synergy.quern.Main.MODULE_ID;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zItems {

    public static void register(IEventBus bus) {
        zItem.register(bus);
        zBlockItem.register(bus);
    }

    public static final DeferredRegister.Items zItem = DeferredRegister.createItems(MODULE_ID);
    public static final DeferredRegister.Items zBlockItem = DeferredRegister.createItems(MODULE_ID);

    public static final DeferredItem<Item> WOODEN_GEAR = zItem.registerSimpleItem("wooden_gear");
  
}
