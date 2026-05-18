package com.synergy.quern.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.synergy.quern.Main;
import com.synergy.quern.init.types.zItemTags;
import com.synergy.quern.init.types.zItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataItemTag extends ItemTagsProvider {

        public DataItemTag(PackOutput o, CompletableFuture<HolderLookup.Provider> p,
                        CompletableFuture<TagLookup<Block>> b,
                        ExistingFileHelper h) {
                super(o, p, b, Main.ID, h);
        }

        @Override
        protected void addTags(Provider p) {

                tag(Tags.Items.DUSTS).add(getItems(zItems.zDusts));

                tag(zItemTags.AMETHYST_DUST).add(zItems.AMETHYST_DUST.get());
                tag(zItemTags.COAL_DUST).add(zItems.CARBON_DUST.get());
                tag(zItemTags.CARBON_DUST).add(zItems.CARBON_DUST.get());
                tag(zItemTags.COPPER_DUST).add(zItems.COPPER_DUST.get());
                tag(zItemTags.DIAMOND_DUST).add(zItems.DIAMOND_DUST.get());
                tag(zItemTags.EMERALD_DUST).add(zItems.EMERALD_DUST.get());
                tag(zItemTags.GOLD_DUST).add(zItems.GOLD_DUST.get());
                tag(zItemTags.IRON_DUST).add(zItems.IRON_DUST.get());
                tag(zItemTags.LAPIS_DUST).add(zItems.LAPIS_DUST.get());
                tag(zItemTags.QUARTZ_DUST).add(zItems.QUARTZ_DUST.get());

                tag(zItemTags.SAWDUST)
                                .add(zItems.SAWDUST.get())
                                .addOptionalTag(zItemTags.SAWDUST2);

                tag(ItemTags.PIGLIN_LOVED)
                                .addOptionalTag(
                                                zItemTags.GOLD_DUST);

                tag(zItemTags.GEARS).add(getItems(zItems.zGears));
                tag(zItemTags.WOODEN_GEAR).add(zItems.WOODEN_GEAR.get());

                tag(zItemTags.FLOUR)
                                .add(zItems.FLOUR.get());

        }

        public Item[] getItems(DeferredRegister.Items items) {
                return items.getEntries().stream().map(DeferredHolder::get).toArray(Item[]::new);
        }

}