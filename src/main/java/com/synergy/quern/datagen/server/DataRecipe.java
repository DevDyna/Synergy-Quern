package com.synergy.quern.datagen.server;

import static com.synergy.quern.Main.ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.setup.registry.zLibrary;
import com.synergy.quern.init.builder.quern.recipe.MillingBuilder;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;

public class DataRecipe extends RecipeProvider {

        protected DataRecipe(Provider registries, RecipeOutput output) {
                super(registries, output);
        }

        @Override
        protected void buildRecipes() {

                ShapedRecipeBuilder.shaped(items, RecipeCategory.REDSTONE, zBlocks.QUERN.get())
                                .pattern("TS ")
                                .pattern("SWS")
                                .define('W', zItems.WOODEN_GEAR.get())
                                .define('S', Items.STONE_SLAB)
                                .define('T', Items.STICK)
                                .unlockedBy(ID, has(zItems.WOODEN_GEAR.get()))
                                .save(output);

                ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, zItems.WOODEN_GEAR.get())
                                .pattern(" S ")
                                .pattern("S S")
                                .pattern(" S ")
                                .define('S', Items.STICK)
                                .unlockedBy(ID, has(Items.STICK))
                                .save(output);

                MillingBuilder.of()
                                .input(Items.COBBLESTONE)
                                .output(Items.GRAVEL)
                                .unlockedBy()
                                .save(output);

                MillingBuilder.of()
                                .input(Items.GRAVEL)
                                .output(Items.SAND)
                                .unlockedBy()
                                .save(output);

                MillingBuilder.of()
                                .input(Items.STONE)
                                .output(Items.COBBLESTONE)
                                .unlockedBy()
                                .save(output);

                MillingBuilder.of()
                                .input(Items.DEEPSLATE)
                                .output(Items.COBBLED_DEEPSLATE)
                                .unlockedBy()
                                .save(output);

                MillingBuilder.of()
                                .input(Items.RAW_COPPER)
                                .output(zLibrary.zItems.COPPER_DUST.get(), 2)
                                .unlockedBy()
                                .save(output, "_from_raw");

                MillingBuilder.of()
                                .input(Items.RAW_GOLD)
                                .output(zLibrary.zItems.GOLD_DUST.get(), 2)
                                .unlockedBy()
                                .save(output, "_from_raw");

                MillingBuilder.of()
                                .input(Items.RAW_IRON)
                                .output(zLibrary.zItems.IRON_DUST.get(), 2)
                                .unlockedBy()
                                .save(output, "_from_raw");

                MillingBuilder.of()
                                .input(Items.COPPER_INGOT)
                                .output(zLibrary.zItems.COPPER_DUST.get())
                                .unlockedBy()
                                .save(output, "_from_ingot");

                MillingBuilder.of()
                                .input(Items.GOLD_INGOT)
                                .output(zLibrary.zItems.GOLD_DUST.get())
                                .unlockedBy()
                                .save(output, "_from_ingot");

                MillingBuilder.of()
                                .input(Items.IRON_INGOT)
                                .output(zLibrary.zItems.IRON_DUST.get())
                                .unlockedBy()
                                .save(output, "_from_ingot");

                MillingBuilder.of()
                                .delay(80)
                                .input(Items.WHEAT)
                                .output(zLibrary.zItems.FLOUR.get())
                                .unlockedBy()
                                .save(output);

        }

        public static final class RecipeRunner extends RecipeProvider.Runner {
                public RecipeRunner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
                        super(output, lookupProvider);
                }

                @Override
                protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider(
                                HolderLookup.Provider lookupProvider,
                                RecipeOutput output) {
                        return new DataRecipe(lookupProvider, output);
                }

                @Override
                public String getName() {
                        return "Synergy";
                }
        }

}