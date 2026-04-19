package com.synergy.quern.datagen.server;

import static com.synergy.quern.Main.ID;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.devdyna.cakesticklib.api.datagen.AdvancementsUtils;
import com.synergy.quern.init.types.zBlocks;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;

public class DataAdvancement extends AdvancementProvider {

        public DataAdvancement(PackOutput output, CompletableFuture<Provider> registries,
                        List<AdvancementSubProvider> subProviders) {
                super(output, registries, subProviders);
        }

        public static class DataAdvancementGenerator implements AdvancementSubProvider {

                @SuppressWarnings("unused")
                @Override
                public void generate(Provider p, Consumer<AdvancementHolder> c) {

                        var quern = AdvancementsUtils
                                        .getExistingParent("minecraft:story/mine_stone", zBlocks.QUERN.get(),
                                                        ID,"quern",
                                                        AdvancementType.TASK, true, true, false)
                                        .addCriterion("craft_quern",
                                                        InventoryChangeTrigger.TriggerInstance
                                                                        .hasItems(zBlocks.QUERN.get()))
                                        .requirements(AdvancementRequirements.allOf(List.of("craft_quern")))
                                        .save(c, ID + ":extend/story/mine_stone/quern");

                }

        }

}
