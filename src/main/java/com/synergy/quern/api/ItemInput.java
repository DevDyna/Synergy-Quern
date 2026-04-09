package com.synergy.quern.api;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record ItemInput(ItemStack input) implements RecipeInput {

    @Override
    public ItemStack getItem(int arg0) {
        return input;
    }

    @Override
    public int size() {
        return 1;
    }
    
}
