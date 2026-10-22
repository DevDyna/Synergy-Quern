package com.synergy.quern.init.builder.quern.recipe;

import static com.synergy.quern.Main.MODULE_ID;

import java.util.LinkedHashMap;
import java.util.Optional;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

public class QuernRecipeBuilder extends BaseRecipeBuilder
        implements ItemAttach.Input.ItemCounted<QuernRecipeBuilder>,
        ItemAttach.Output.SimpleOutputItem<QuernRecipeBuilder> {

    private SizedIngredient input;
    private int tick;
    private Optional<Double> speed;
    private ItemStackTemplate output;

    private QuernRecipeBuilder(HolderLookup.Provider p) {
        super(p);
        this.tick = 20;
        this.speed = Optional.empty();
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static QuernRecipeBuilder of(HolderLookup.Provider p) {
        return new QuernRecipeBuilder(p);
    }

    public QuernRecipeBuilder input(SizedIngredient input) {
        this.input = input;
        return this;
    }

    public QuernRecipeBuilder output(ItemStackTemplate output) {
        this.output = output;
        return this;
    }

    public QuernRecipeBuilder delay(int tick) {
        this.tick = tick;
        return this;
    }

    public QuernRecipeBuilder speed(double speed) {
        this.speed = Optional.ofNullable(speed);
        return this;
    }

    public QuernRecipeBuilder unlockedBy() {
        return unlockedBy(MODULE_ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(input.ingredient().getValues().stream()
                        .map(Holder::getKey)
                        .map(ResourceKey::identifier)
                        .map(BuiltInRegistries.ITEM::getValue)
                        .toArray(Item[]::new)));
    }

    public QuernRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new QuernRecipe(input, tick, speed, output);
    }

    @Override
    public QuernRecipeBuilder getBuilder() {
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "quern/" + output.item().getKey().identifier().getPath()
                + extra);
    }

}