package com.synergy.quern.init.builder.quern;

import java.util.List;


import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.synergy.quern.api.MonoItemInput;
import com.synergy.quern.api.RecipeRegister;
import com.synergy.quern.api.recipes.types.BaseRecipeType;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zRecipeTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public class QuernMillingRecipe extends BaseRecipeType<MonoItemInput> {

    private final Ingredient input;
    private final int time;
    private final ItemStack output;

    public QuernMillingRecipe(Ingredient input,
            ItemStack output, int time) {
        this.input = input;
        this.time = time;
        this.output = output;
    }

    public static QuernMillingRecipe of(Ingredient input, ItemStack output, int time) {
        return new QuernMillingRecipe(input, output, time);
    }

    public boolean matches(MonoItemInput r, Level l) {
        return this.input.test(r.input());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return this.output.copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(this.input));
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getTime() {
        return time;
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return this.output;
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<MonoItemInput>> getRecipe() {
        return zRecipeTypes.QUERN_MILLING;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.QUERN.get().asItem();
    }

    public static class Serializer implements RecipeSerializer<QuernMillingRecipe> {

    public static final MapCodec<QuernMillingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(QuernMillingRecipe::getInput),
            ItemStack.CODEC.fieldOf("result").forGetter(QuernMillingRecipe::getOutput),
                        Codec.intRange(1, Integer.MAX_VALUE).fieldOf("frequence").forGetter(QuernMillingRecipe::getTime)
            ).apply(inst, QuernMillingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, QuernMillingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC, QuernMillingRecipe::getInput,
                    ItemStack.STREAM_CODEC, QuernMillingRecipe::getOutput,
                    ByteBufCodecs.INT, QuernMillingRecipe::getTime,
                    QuernMillingRecipe::new);

    @Override
    public MapCodec<QuernMillingRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, QuernMillingRecipe> streamCodec() {
        return STREAM_CODEC;
    }

}
}
