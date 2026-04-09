package com.synergy.quern.init.builder.quern.recipe;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.synergy.quern.api.BaseRecipeType;
import com.synergy.quern.api.ItemInput;
import com.synergy.quern.api.RecipeRegister;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zRecipeTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.datafix.fixes.ItemStackTagFix;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeSerializer;

@SuppressWarnings("null")
public class MillingRecipe extends BaseRecipeType<ItemInput> {

    private final Ingredient input;
    private final int time;
    private final ItemStackTemplate output;

    public MillingRecipe(Ingredient input,
            ItemStackTemplate output, int time) {
        this.input = input;
        this.time = time;
        this.output = output;
    }

    public static MillingRecipe of(Ingredient input, ItemStackTemplate output, int time) {
        return new MillingRecipe(input, output, time);
    }

    public boolean matches(ItemInput r, Level l) {
        return this.input.test(r.input());
    }

    @Override
    public ItemStack assemble(ItemInput r) {
        return this.output.create().copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(this.input));
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStackTemplate getOutput() {
        return output;
    }

    public int getTime() {
        return time;
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<ItemInput>> getRecipe() {
        return zRecipeTypes.QUERN;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.QUERN.get().asItem();
    }

    public static final RecipeSerializer<MillingRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<MillingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(MillingRecipe::getInput),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(MillingRecipe::getOutput),
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("frequence").forGetter(MillingRecipe::getTime))
            .apply(inst, MillingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, MillingRecipe> STREAM_CODEC = StreamCodec
            .composite(
                    Ingredient.CONTENTS_STREAM_CODEC, MillingRecipe::getInput,
                    ItemStackTemplate.STREAM_CODEC, MillingRecipe::getOutput,
                    ByteBufCodecs.INT, MillingRecipe::getTime,
                    MillingRecipe::new);

}
