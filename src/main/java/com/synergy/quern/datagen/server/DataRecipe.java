package com.synergy.quern.datagen.server;

import java.util.concurrent.CompletableFuture;

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
import net.neoforged.neoforge.common.Tags;

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
                                .define('T', Tags.Items.RODS_WOODEN)
                                .unlockedBy("craft_quern", has(zItems.WOODEN_GEAR.get()))
                                .save(output);

                shaped(RecipeCategory.MISC, zItems.WOODEN_GEAR.get())
                                .pattern(" S ")
                                .pattern("S S")
                                .pattern(" S ")
                                .define('S', Tags.Items.RODS_WOODEN)
                                .unlockedBy("craft_wooden_gear", has(Tags.Items.RODS_WOODEN))
                                .save(output);

                MillingBuilder.of()
                                .input(Tags.Items.COBBLESTONES_NORMAL, registries)
                                .output(Items.GRAVEL)
                                .unlockedBy("has_cobblestone", has(Tags.Items.COBBLESTONES_NORMAL))
                                .save(output);

                MillingBuilder.of()
                                .input(Tags.Items.GRAVELS, registries)
                                .output(Items.SAND)
                                .unlockedBy("has_gravel", has(Tags.Items.GRAVELS))
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
                                .input(Tags.Items.RAW_MATERIALS_COPPER, registries)
                                .output(zLibrary.zItems.COPPER_DUST.get(), 2)
                                .unlockedBy("has_raw_copper", has(Tags.Items.RAW_MATERIALS_COPPER))
                                .save(output, "_from_raw");

                MillingBuilder.of()
                                .input(Tags.Items.RAW_MATERIALS_GOLD, registries)
                                .output(zLibrary.zItems.GOLD_DUST.get(), 2)
                                .unlockedBy("has_raw_gold", has(Tags.Items.RAW_MATERIALS_GOLD))
                                .save(output, "_from_raw");

                MillingBuilder.of()
                                .input(Tags.Items.RAW_MATERIALS_IRON, registries)
                                .output(zLibrary.zItems.IRON_DUST.get(), 2)
                                .unlockedBy("has_raw_iron", has(Tags.Items.RAW_MATERIALS_IRON))
                                .save(output, "_from_raw");

                MillingBuilder.of()
                                .input(Tags.Items.INGOTS_COPPER, registries)
                                .output(zLibrary.zItems.COPPER_DUST.get())
                                .unlockedBy("has_copper_ingot", has(Tags.Items.INGOTS_COPPER))
                                .save(output, "_from_ingot");

                MillingBuilder.of()
                                .input(Tags.Items.INGOTS_GOLD, registries)
                                .output(zLibrary.zItems.GOLD_DUST.get())
                                .unlockedBy("has_gold_ingot", has(Tags.Items.INGOTS_GOLD))
                                .save(output, "_from_ingot");

                MillingBuilder.of()
                                .input(Tags.Items.INGOTS_IRON, registries)
                                .output(zLibrary.zItems.IRON_DUST.get())
                                .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                                .save(output, "_from_ingot");

                MillingBuilder.of()
                                .input(Tags.Items.CROPS_WHEAT, registries)
                                .delay(80)
                                .output(zLibrary.zItems.FLOUR.get())
                                .unlockedBy("has_wheat", has(Tags.Items.CROPS_WHEAT))
                                .save(output);

                MillingBuilder.of()
                                .input(ItemTags.COALS, registries)
                                .output(zLibrary.zItems.CARBON_DUST.get())
                                .unlockedBy("has_coal", has(ItemTags.COALS))
                                .save(output);

                MillingBuilder.of()
                                .input(Tags.Items.CROPS_SUGAR_CANE, registries)
                                .output(Items.SUGAR, 2)
                                .unlockedBy("has_sugar_cane", has(Tags.Items.CROPS_SUGAR_CANE))
                                .save(output);

                MillingBuilder.of()
                                .input(ItemTags.LOGS, registries)
                                .output(zLibrary.zItems.SAWDUST.get(), 2)
                                .unlockedBy("has_log", has(ItemTags.LOGS))
                                .save(output);

                MillingBuilder.of()
                                .input(Tags.Items.GEMS_QUARTZ, registries)
                                .output(zLibrary.zItems.QUARTZ_DUST.get())
                                .unlockedBy("has_quartz", has(Tags.Items.GEMS_QUARTZ))
                                .save(output);
                MillingBuilder.of()
                                .input(Tags.Items.GEMS_LAPIS, registries)
                                .output(zLibrary.zItems.LAPIS_DUST.get())
                                .unlockedBy("has_lapis", has(Tags.Items.GEMS_LAPIS))
                                .save(output);
                MillingBuilder.of()
                                .input(Tags.Items.GEMS_EMERALD, registries)
                                .output(zLibrary.zItems.EMERALD_DUST.get())
                                .unlockedBy("has_emerald", has(Tags.Items.GEMS_EMERALD))
                                .save(output);
                MillingBuilder.of()
                                .input(Tags.Items.GEMS_DIAMOND, registries)
                                .output(zLibrary.zItems.DIAMOND_DUST.get())
                                .unlockedBy("has_diamond", has(Tags.Items.GEMS_DIAMOND))
                                .save(output);

                MillingBuilder.of()
                                .input(Tags.Items.GEMS_AMETHYST, registries)
                                .output(zLibrary.zItems.AMETHYST_DUST.get())
                                .unlockedBy("has_amethyst", has(Tags.Items.GEMS_AMETHYST))
                                .save(output);

                MillingBuilder.of()
                                .input(Items.PRISMARINE_SHARD)
                                .output(Items.PRISMARINE_CRYSTALS)
                                .unlockedBy("has_prismarine", has(Items.PRISMARINE_CRYSTALS))
                                .save(output);

                MillingBuilder.of()
                                .input(Items.AMETHYST_BLOCK)
                                .output(Items.AMETHYST_SHARD, 4)
                                .unlockedBy("has_amethyst_block", has(Items.AMETHYST_BLOCK))
                                .save(output);

        }

        public static final class RecipeRunner extends RecipeProvider.Runner {
                public RecipeRunner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
                        super(output, lookupProvider);
                }

                @Override
                protected RecipeProvider createRecipeProvider(
                                HolderLookup.Provider lookupProvider,
                                RecipeOutput output) {
                        return new DataRecipe(lookupProvider, output);
                }

                @Override
                public String getName() {
                        return "Synergy Quern";
                }
        }

}