package com.synergy.quern.api.aspect;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public interface ItemStorageBlock {

    ItemStacksResourceHandler getItemStorage();

    int getSlots();

    default ItemStack getStackInSlot(int index) {
        return getItemStorage().getResource(index).toStack(getItemStorage().getAmountAsInt(index));
    }

}
