package com.synergy.quern.init.types;

import com.synergy.quern.api.utils.x;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;

public class zItemTags {

    public static void register(IEventBus bus) {
    }

    // sawdust
    public static final TagKey<Item> SAWDUST = tagItem("c", "dusts/wood");
    public static final TagKey<Item> SAWDUST2 = tagItem("c", "dusts/saw");
    // dusts
    public static final TagKey<Item> GOLD_DUST = tagItem("c", "dusts/gold");
    public static final TagKey<Item> IRON_DUST = tagItem("c", "dusts/iron");
    public static final TagKey<Item> EMERALD_DUST = tagItem("c", "dusts/emerald");
    public static final TagKey<Item> QUARTZ_DUST = tagItem("c", "dusts/quartz");
    public static final TagKey<Item> DIAMOND_DUST = tagItem("c", "dusts/diamond");
    public static final TagKey<Item> AMETHYST_DUST = tagItem("c", "dusts/amethyst");
    public static final TagKey<Item> COPPER_DUST = tagItem("c", "dusts/copper");
    public static final TagKey<Item> COAL_DUST = tagItem("c", "dusts/coal");
    public static final TagKey<Item> CARBON_DUST = tagItem("c", "dusts/carbon");
    public static final TagKey<Item> LAPIS_DUST = tagItem("c", "dusts/lapis");

    // gears
    public static final TagKey<Item> GEARS = tagItem("c", "gears");
    public static final TagKey<Item> WOODEN_GEAR = tagItem("c", "gears/wooden");

    //
    public static final TagKey<Item> FLOUR = tagItem("c", "flour");

    public static TagKey<Item> tagItem(String modname, String id) {
        return TagKey.create(BuiltInRegistries.ITEM.key(),
                x.rl(modname, id));
    }

}
