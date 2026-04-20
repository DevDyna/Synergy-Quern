package com.synergy.quern.datagen;

import static com.synergy.quern.Main.ID;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.CakeStickLib;
import com.synergy.quern.datagen.client.*;
import com.synergy.quern.datagen.server.*;
import com.synergy.quern.datagen.server.DataAdvancement.DataAdvancementGenerator;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.*;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ID)
public class Controller {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client e) {
        DataGenerator gen = e.getGenerator();
        CompletableFuture<HolderLookup.Provider> provider = e.getLookupProvider();
        // PackGenerator v = gen.getVanillaPack(true);
        var output = gen.getPackOutput();

        e.createDatapackRegistryObjects(new RegistrySetBuilder(), Set.of("minecraft", ID, CakeStickLib.MODULE_ID));

        // client

        // providerGen(e, g, new DataBlockModelState(po, f));
        // providerGen(e, g, new DataItemModel(po, f));
        e.addProvider(new DataLang(output));

        // server

        e.addProvider(new DataAdvancement(output, provider, List.of(new DataAdvancementGenerator())));

        e.addProvider(new DataBlockTag(output, provider));
        // v.addProvider(o -> new DataItemTag(o, pr));

        e.addProvider(new LootTableProvider(output, Set.of(),
                List.of(
                        new LootTableProvider.SubProviderEntry(DataLootBlock::new, LootContextParamSets.BLOCK)),
                provider));

        // v.addProvider(o -> new DataRecipe.RecipeRunner(o, pr));

        e.createProvider(DataRecipe.RecipeRunner::new);

        
    }

}