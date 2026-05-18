package com.synergy.quern.datagen.server;

import static com.synergy.quern.Main.ID;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.synergy.quern.api.utils.DataGenUtil;
import com.synergy.quern.init.types.zBlocks;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

@SuppressWarnings({ "null", "unused" })
public class DataAdvancement extends AdvancementProvider {

        public DataAdvancement(PackOutput output, CompletableFuture<Provider> registries,
                        ExistingFileHelper existingFileHelper) {
                super(output, registries, existingFileHelper, List.of(new DataAdvancementGenerator()));
        }

        public static class DataAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {

                @Override
                public void generate(Provider p, Consumer<AdvancementHolder> h, ExistingFileHelper e) {

                        var quern = DataGenUtil
                                        .getExistingParent("minecraft:story/mine_stone", zBlocks.QUERN.get(),
                                                        "quern",
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_quern",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.QUERN.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_quern")))
                                        .save(h, ID + ":extend/story/mine_stone/quern");

                }

        }

}
