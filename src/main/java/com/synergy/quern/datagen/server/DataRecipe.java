package com.synergy.quern.datagen.server;

import static com.synergy.quern.Main.ID;

import java.util.concurrent.CompletableFuture;

import com.synergy.quern.api.utils.x;
import com.synergy.quern.init.builder.quern.QuernMillingBuilder;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zItemTags;
import com.synergy.quern.init.types.zItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

public class DataRecipe extends RecipeProvider {

        public DataRecipe(PackOutput o, CompletableFuture<HolderLookup.Provider> c) {
                super(o, c);
        }

        @Override
        protected void buildRecipes(RecipeOutput output) {

                ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, zBlocks.QUERN.get())
                                .pattern("TS ")
                                .pattern("SWS")
                                .define('W', zItemTags.WOODEN_GEAR)
                                .define('S', Items.STONE_SLAB)
                                .define('T', Tags.Items.RODS_WOODEN)
                                .unlockedBy("craft_quern", has(zItemTags.WOODEN_GEAR))
                                .save(output);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, zItems.WOODEN_GEAR.get())
                                .pattern(" # ")
                                .pattern("# #")
                                .pattern(" # ")
                                .define('#', ItemTags.PLANKS)
                                .unlockedBy("craft_wooden_gear", has(zItemTags.WOODEN_GEAR))
                                .save(output);

                QuernMillingBuilder.of()
                                .input(Tags.Items.COBBLESTONES_NORMAL)
                                .output(Items.GRAVEL)
                                .unlockedBy("has_cobblestone", has(Tags.Items.COBBLESTONES_NORMAL))
                                .save(output);

                QuernMillingBuilder.of()
                                .input(Tags.Items.GRAVELS)
                                .output(Items.SAND)
                                .unlockedBy("has_gravel", has(Tags.Items.GRAVELS))
                                .save(output);

                QuernMillingBuilder.of()
                                .input(Items.STONE)
                                .output(Items.COBBLESTONE)
                                .unlockedBy()
                                .save(output);

                QuernMillingBuilder.of()
                                .input(Items.DEEPSLATE)
                                .output(Items.COBBLED_DEEPSLATE)
                                .unlockedBy()
                                .save(output);

                QuernMillingBuilder.of()
                                .input(Tags.Items.RAW_MATERIALS_COPPER)
                                .output(zItems.COPPER_DUST.get(), 2)
                                .unlockedBy("has_raw_copper", has(Tags.Items.RAW_MATERIALS_COPPER))
                                .save(output, "_from_raw");

                QuernMillingBuilder.of()
                                .input(Tags.Items.RAW_MATERIALS_GOLD)
                                .output(zItems.GOLD_DUST.get(), 2)
                                .unlockedBy("has_raw_gold", has(Tags.Items.RAW_MATERIALS_GOLD))
                                .save(output, "_from_raw");

                QuernMillingBuilder.of()
                                .input(Tags.Items.RAW_MATERIALS_IRON)
                                .output(zItems.IRON_DUST.get(), 2)
                                .unlockedBy("has_raw_iron", has(Tags.Items.RAW_MATERIALS_IRON))
                                .save(output, "_from_raw");

                QuernMillingBuilder.of()
                                .input(Tags.Items.INGOTS_COPPER)
                                .output(zItems.COPPER_DUST.get())
                                .unlockedBy("has_copper_ingot", has(Tags.Items.INGOTS_COPPER))
                                .save(output, "_from_ingot");

                QuernMillingBuilder.of()
                                .input(Tags.Items.INGOTS_GOLD)
                                .output(zItems.GOLD_DUST.get())
                                .unlockedBy("has_gold_ingot", has(Tags.Items.INGOTS_GOLD))
                                .save(output, "_from_ingot");

                QuernMillingBuilder.of()
                                .input(Tags.Items.INGOTS_IRON)
                                .output(zItems.IRON_DUST.get())
                                .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                                .save(output, "_from_ingot");

                QuernMillingBuilder.of()
                                .input(Tags.Items.CROPS_WHEAT)
                                .delay(80)
                                .output(zItems.FLOUR.get())
                                .unlockedBy("has_wheat", has(Tags.Items.CROPS_WHEAT))
                                .save(output);

                QuernMillingBuilder.of()
                                .input(ItemTags.COALS)
                                .output(zItems.CARBON_DUST.get())
                                .unlockedBy("has_coal", has(ItemTags.COALS))
                                .save(output);

                QuernMillingBuilder.of()
                                .input(Tags.Items.CROPS_SUGAR_CANE)
                                .output(Items.SUGAR, 2)
                                .unlockedBy("has_sugar_cane", has(Tags.Items.CROPS_SUGAR_CANE))
                                .save(output);

                QuernMillingBuilder.of()
                                .input(ItemTags.LOGS)
                                .output(zItems.SAWDUST.get(), 2)
                                .unlockedBy("has_log", has(ItemTags.LOGS))
                                .save(output);

                QuernMillingBuilder.of()
                                .input(Tags.Items.GEMS_QUARTZ)
                                .output(zItems.QUARTZ_DUST.get())
                                .unlockedBy("has_quartz", has(Tags.Items.GEMS_QUARTZ))
                                .save(output);
                QuernMillingBuilder.of()
                                .input(Tags.Items.GEMS_LAPIS)
                                .output(zItems.LAPIS_DUST.get())
                                .unlockedBy("has_lapis", has(Tags.Items.GEMS_LAPIS))
                                .save(output);
                QuernMillingBuilder.of()
                                .input(Tags.Items.GEMS_EMERALD)
                                .output(zItems.EMERALD_DUST.get())
                                .unlockedBy("has_emerald", has(Tags.Items.GEMS_EMERALD))
                                .save(output);
                QuernMillingBuilder.of()
                                .input(Tags.Items.GEMS_DIAMOND)
                                .output(zItems.DIAMOND_DUST.get())
                                .unlockedBy("has_diamond", has(Tags.Items.GEMS_DIAMOND))
                                .save(output);

                QuernMillingBuilder.of()
                                .input(Tags.Items.GEMS_AMETHYST)
                                .output(zItems.AMETHYST_DUST.get())
                                .unlockedBy("has_amethyst", has(Tags.Items.GEMS_AMETHYST))
                                .save(output);

                QuernMillingBuilder.of()
                                .input(Items.PRISMARINE_SHARD)
                                .output(Items.PRISMARINE_CRYSTALS)
                                .unlockedBy("has_prismarine", has(Items.PRISMARINE_CRYSTALS))
                                .save(output);

                QuernMillingBuilder.of()
                                .input(Items.AMETHYST_BLOCK)
                                .output(Items.AMETHYST_SHARD, 4)
                                .unlockedBy("has_amethyst_block", has(Items.AMETHYST_BLOCK))
                                .save(output);

                simpleCooking(output, zItems.FLOUR.get(), Items.BREAD);

                simpleCooking(output, zItems.COPPER_DUST.get(), Items.COPPER_INGOT);
                simpleCooking(output, zItems.IRON_DUST.get(), Items.IRON_INGOT);
                simpleCooking(output, zItems.GOLD_DUST.get(), Items.GOLD_INGOT);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PAPER, 6)
                                .define('#', zItems.SAWDUST.get())
                                .pattern("###")
                                .unlockedBy("has_sawdust", has(zItems.SAWDUST.get()))
                                .save(output, ID + ":"
                                                + getConversionRecipeName(
                                                                Items.PAPER,
                                                                zItems.SAWDUST.get()));

        }

        public void simpleCooking(RecipeOutput c, Item input, Item output) {
                SimpleCookingRecipeBuilder
                                .smelting(x.ingredient(input),
                                                RecipeCategory.MISC,
                                                output, 0.1F, 200)
                                .unlockedBy(getHasName(input),
                                                has(input))
                                .save(c, "quern:"
                                                + getConversionRecipeName(
                                                                output,
                                                                input));
        }

}