package com.synergy.quern.init.types;

import static com.synergy.quern.Main.ID;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zItems {

    public static void register(IEventBus bus) {
        zBlockItem.register(bus);

        zSimple.register(bus);
        zDusts.register(bus);
        
        zGears.register(bus);
    }

    public static final DeferredRegister.Items zBlockItem = DeferredRegister
            .createItems(ID);

    public static final DeferredRegister.Items zSimple = DeferredRegister
            .createItems(ID);

    public static final DeferredRegister.Items zDusts = DeferredRegister
            .createItems(ID);
    
    public static final DeferredRegister.Items zGears = DeferredRegister
            .createItems(ID);


    public static final DeferredHolder<Item, Item> SAWDUST = zSimple.registerSimpleItem("sawdust");

    public static final DeferredHolder<Item, Item> FLOUR = zSimple.registerSimpleItem("flour");

    public static final DeferredHolder<Item, Item> AMETHYST_DUST = zDusts
            .registerSimpleItem("amethyst_dust");

    public static final DeferredHolder<Item, Item> CARBON_DUST = zDusts.registerSimpleItem("carbon_dust");
    public static final DeferredHolder<Item, Item> COPPER_DUST = zDusts.registerSimpleItem("copper_dust");
    public static final DeferredHolder<Item, Item> DIAMOND_DUST = zDusts.registerSimpleItem("diamond_dust");
    public static final DeferredHolder<Item, Item> EMERALD_DUST = zDusts.registerSimpleItem("emerald_dust");
    public static final DeferredHolder<Item, Item> GOLD_DUST = zDusts.registerSimpleItem("gold_dust");
    public static final DeferredHolder<Item, Item> IRON_DUST = zDusts.registerSimpleItem("iron_dust");
    public static final DeferredHolder<Item, Item> LAPIS_DUST = zDusts.registerSimpleItem("lapis_dust");
    public static final DeferredHolder<Item, Item> QUARTZ_DUST = zDusts.registerSimpleItem("quartz_dust");

   
    public static final DeferredHolder<Item, Item> WOODEN_GEAR = zGears
            .registerSimpleItem("wooden_gear");

}
