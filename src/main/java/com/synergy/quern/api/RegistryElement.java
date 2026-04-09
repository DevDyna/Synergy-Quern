package com.synergy.quern.api;

import java.util.function.Supplier;

import com.synergy.quern.init.Material;
import com.synergy.quern.init.types.*;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

public class RegistryElement<RECIPE extends Recipe<?>> {

    private DeferredItem<BlockItem> item;
    private DeferredBlock<Block> block;
//    private DeferredHolder<BlockEntityType<?>, BlockEntityType<BlockEntity>> be;
    private RecipeRegister<RECIPE> recipe;

   public RegistryElement(String id, 
   Supplier<Block> block,
    // BlockEntitySupplier<? extends BlockEntity> beSupplier,
                       Supplier<? extends RecipeSerializer<RECIPE>> serializer) {
    this.block = zBlocks.zBlockItem.register(id, block);
    this.item = zItems.zBlockItem.registerSimpleBlockItem(this.block);
    // this.be = Material.createBlockEntity(id, beSupplier, this.block);
    this.recipe = RecipeRegister.of(id, serializer);
}

    public static <RECIPE extends Recipe<?>> RegistryElement<RECIPE> of(String id, Supplier<Block> block,
            // BlockEntitySupplier<BE> be,
            Supplier<? extends RecipeSerializer<RECIPE>> serializer) {
        return new RegistryElement<>(id, block,
        //  be,
         serializer);
    }

    public DeferredItem<BlockItem> item() {
        return item;
    }

    public DeferredBlock<Block> block() {
        return block;
    }

    // public DeferredHolder<BlockEntityType<?>, ?> be() {
    //     return be;
    // }

    public RecipeRegister<RECIPE> recipe() {
        return recipe;
    }

}
