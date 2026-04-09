package com.synergy.quern.datagen;

import static com.synergy.quern.Main.ID;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.synergy.quern.datagen.client.*;
import com.synergy.quern.datagen.server.*;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.*;
import net.minecraft.data.DataGenerator.PackGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ID)
public class Controller {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client e) {
        DataGenerator g = e.getGenerator();
        CompletableFuture<HolderLookup.Provider> pr = e.getLookupProvider();
        PackGenerator v = g.getVanillaPack(true);

        // client

        // providerGen(e, g, new DataBlockModelState(po, f));
        // providerGen(e, g, new DataItemModel(po, f));
        v.addProvider(DataLang::new);

        // server

        v.addProvider(o -> new DataBlockTag(o, pr));

        v.addProvider(o -> new LootTableProvider(o, Set.of(),
                List.of(
                        new LootTableProvider.SubProviderEntry(DataLootBlock::new, LootContextParamSets.BLOCK)),
                pr));

        v.addProvider(o -> new DataRecipe.RecipeRunner(o, pr));

    }

}