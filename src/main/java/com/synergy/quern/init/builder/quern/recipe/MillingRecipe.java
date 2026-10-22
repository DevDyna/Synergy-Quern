package com.synergy.quern.init.builder.quern.recipe;

import static com.synergy.quern.Main.MODULE_ID;

import java.util.List;
import java.util.Optional;

import com.devdyna.cakesticklib.api.recipe.recipeInput.ItemInput;
import com.devdyna.cakesticklib.api.recipe.recipeType.BaseRecipeType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zRecipeTypes;

import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

@SuppressWarnings("null")
public class MillingRecipe extends BaseRecipeType<ItemInput.simple> {

    private final SizedIngredient input;
    private final int time;
    private final Optional<Double> speed;
    private final ItemStackTemplate output;

    public MillingRecipe(SizedIngredient input,
            int time, Optional<Double> speed, ItemStackTemplate output) {
        this.input = input;
        this.time = time;
        this.output = output;
        this.speed = speed;
    }

    public static MillingRecipe of(SizedIngredient input,
            int time, Optional<Double> speed, ItemStackTemplate output) {
        return new MillingRecipe(input, time, speed, output);
    }

    public boolean matches(ItemInput.simple r, Level l) {
        return this.input.test(r.item());
    }

    @Override
    public ItemStack assemble(ItemInput.simple r) {
        return this.output.create().copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(this.input.ingredient()));
    }

    public SizedIngredient getInput() {
        return input;
    }

    public ItemStackTemplate getOutput() {
        return output;
    }

    public int getTime() {
        return time;
    }

    public Optional<Double> getSpeed() {
        return speed;
    }

    @Override
    public RecipeType<? extends Recipe<ItemInput.simple>> getType() {
        return zRecipeTypes.QUERN.getType();
    }

    @Override
    public RecipeSerializer<? extends Recipe<ItemInput.simple>> getSerializer() {
        return zRecipeTypes.QUERN.getSerializer();
    }

    @Override
    public String group() {
        return MODULE_ID;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.QUERN.get().asItem();
    }

    public static final RecipeSerializer<MillingRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<MillingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            SizedIngredient.NESTED_CODEC.fieldOf("input").forGetter(MillingRecipe::getInput),
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(MillingRecipe::getTime),
            Codec.doubleRange(1, Integer.MAX_VALUE).optionalFieldOf("min_speed_required").forGetter(MillingRecipe::getSpeed),
            ItemStackTemplate.CODEC.fieldOf("output").forGetter(MillingRecipe::getOutput))
            .apply(inst, MillingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, MillingRecipe> STREAM_CODEC = StreamCodec
            .composite(
                    SizedIngredient.STREAM_CODEC, MillingRecipe::getInput,
                    ByteBufCodecs.INT, MillingRecipe::getTime,
                    ByteBufCodecs.optional(ByteBufCodecs.DOUBLE), MillingRecipe::getSpeed,
                    ItemStackTemplate.STREAM_CODEC, MillingRecipe::getOutput,
                    MillingRecipe::new);

}
