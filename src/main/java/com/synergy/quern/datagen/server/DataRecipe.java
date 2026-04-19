package com.synergy.quern.datagen.server;

import static com.synergy.quern.Main.ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.zLibrary;
import com.synergy.quern.init.builder.quern.recipe.MillingBuilder;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
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

                MillingBuilder.simple(Items.COBBLESTONE, Items.GRAVEL, output);
                MillingBuilder.simple(Items.GRAVEL, Items.SAND, output);
                MillingBuilder.simple(Items.STONE, Items.COBBLESTONE, output);
                MillingBuilder.simple(Items.DEEPSLATE, Items.COBBLED_DEEPSLATE, output);

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
                                .input(Items.WHEAT)
                                .delay(80)
                                .output(zLibrary.zItems.FLOUR.get())
                                .unlockedBy()
                                .save(output);

                MillingBuilder.simple(Items.SUGAR_CANE, Items.SUGAR, 2, output);

                MillingBuilder.simple(x.itemIngredient(zLibrary.zItemTags.COAL_LIKE), zLibrary.zItems.CARBON_DUST.get(),
                                output);
                MillingBuilder.simple(x.itemIngredient(ItemTags.LOGS), zLibrary.zItems.SAWDUST.get(), 2, output);
                MillingBuilder.simple(Items.QUARTZ, zLibrary.zItems.QUARTZ_DUST.get(), output);
                MillingBuilder.simple(Items.LAPIS_LAZULI, zLibrary.zItems.QUARTZ_DUST.get(), output);
                MillingBuilder.simple(Items.EMERALD, zLibrary.zItems.EMERALD_DUST.get(), output);
                MillingBuilder.simple(Items.DIAMOND, zLibrary.zItems.DIAMOND_DUST.get(), output);

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