package com.synergy.quern.init.builder.quern.recipe;

import static com.synergy.quern.Main.MODULE_ID;

import java.util.LinkedHashMap;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

public class MillingBuilder extends BaseRecipeBuilder
        implements ItemAttach.Input.NoItemCount<MillingBuilder>,
        ItemAttach.Output.SimpleOutputItem<MillingBuilder> {

    private Ingredient input;
    private int tick;
    private ItemStackTemplate output;

    private MillingBuilder() {
        this.tick = 20;
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static MillingBuilder of() {
        return new MillingBuilder();
    }

    @Deprecated
    public MillingBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public MillingBuilder output(ItemStackTemplate output) {
        this.output = output;
        return this;
    }

    public MillingBuilder delay(int tick) {
        this.tick = tick;
        return this;
    }

    public MillingBuilder unlockedBy() {
        return unlockedBy(MODULE_ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(input.getValues().stream()
                        .map(Holder::getKey)
                        .map(ResourceKey::identifier)
                        .map(BuiltInRegistries.ITEM::getValue)
                        .toArray(Item[]::new)));
    }

    public MillingBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new MillingRecipe(input, output, tick);
    }

    @Override
    public MillingBuilder getBuilder() {
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "quern/" + output.item().getKey().identifier().getPath()
                + extra);
    }

}