package com.synergy.quern.datagen.server;

import static com.synergy.quern.Main.ID;

import java.util.concurrent.CompletableFuture;

import com.synergy.quern.init.builder.quern.recipe.MillingBuilder;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

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
                                .output(zItems.DUST_COPPER.get(), 2)
                                .unlockedBy()
                                .save(output,"_from_raw");

                MillingBuilder.of()
                                .input(Items.RAW_GOLD)
                                .output(zItems.DUST_GOLD.get(), 2)
                                .unlockedBy()
                                .save(output,"_from_raw");

                MillingBuilder.of()
                                .input(Items.RAW_IRON)
                                .output(zItems.DUST_IRON.get(), 2)
                                .unlockedBy()
                                .save(output,"_from_raw");

                MillingBuilder.of()
                                .input(Items.COPPER_INGOT)
                                .output(zItems.DUST_COPPER.get())
                                .unlockedBy()
                                .save(output,"_from_ingot");

                MillingBuilder.of()
                                .input(Items.GOLD_INGOT)
                                .output(zItems.DUST_GOLD.get())
                                .unlockedBy()
                                .save(output,"_from_ingot");

                MillingBuilder.of()
                                .input(Items.IRON_INGOT)
                                .output(zItems.DUST_IRON.get())
                                .unlockedBy()
                                .save(output,"_from_ingot");

                MillingBuilder.of()
                                .delay(80)
                                .input(Items.WHEAT)
                                .output(zItems.FLOUR.get())
                                .unlockedBy()
                                .save(output);

                cooking(output, zItems.FLOUR.get(), Items.BREAD);

                cooking(output, zItems.DUST_COPPER.get(), Items.COPPER_INGOT);
                cooking(output, zItems.DUST_GOLD.get(), Items.GOLD_INGOT);
                cooking(output, zItems.DUST_IRON.get(), Items.IRON_INGOT);

        }

        private void cooking(RecipeOutput c, Item input, Item output) {
                SimpleCookingRecipeBuilder
                                .smelting(Ingredient.of(input),
                                                RecipeCategory.BUILDING_BLOCKS,
                                                CookingBookCategory.FOOD, output, 0.1F, 200)
                                .unlockedBy(getHasName(input),
                                                has(input))
                                .save(c, ID + ":"
                                                + getConversionRecipeName(
                                                                output,
                                                                input));
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