package com.synergy.quern.init.types;

import static com.synergy.quern.Main.ID;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zItems {

    public static void register(IEventBus bus) {
        zItem.register(bus);
        zIngredients.register(bus);
        zBlockItem.register(bus);
    }

    public static final DeferredRegister.Items zIngredients = DeferredRegister.createItems(ID);
    public static final DeferredRegister.Items zItem = DeferredRegister.createItems(ID);
    public static final DeferredRegister.Items zBlockItem = DeferredRegister.createItems(ID);

    public static final DeferredItem<Item> WOODEN_GEAR = zIngredients.registerSimpleItem("wooden_gear");
  
    public static final DeferredItem<Item> DUST_IRON = zIngredients.registerSimpleItem("iron_dust");
    public static final DeferredItem<Item> DUST_COPPER = zIngredients.registerSimpleItem("copper_dust");
    public static final DeferredItem<Item> DUST_GOLD = zIngredients.registerSimpleItem("gold_dust");
  
    public static final DeferredItem<Item> FLOUR = zIngredients.registerSimpleItem("flour");

}
