package com.synergy.quern.api;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;

public class TileObject<RECIPE extends Recipe<?>> {

    private RegistryElement<RECIPE> registry;

    public TileObject(String id, Supplier<Block> block,
    //  BlockEntitySupplier<BE> be,
     Supplier<? extends RecipeSerializer<RECIPE>> serializer) {
        this.registry = RegistryElement.of(id, block,
        //  be,
         serializer);
    }

    public TileObject(RegistryElement<RECIPE> r) {
        this.registry = r;
    }

    public static <RECIPE extends Recipe<?>> TileObject<RECIPE> of(String id, Supplier<Block> block,
            // BlockEntitySupplier<BE> be,
            Supplier<? extends RecipeSerializer<RECIPE>> serializer) {
        return new TileObject<>(id, block,
        //  be,
        serializer);
    }

    public static <RECIPE extends Recipe<?>> TileObject<RECIPE> of(RegistryElement<RECIPE> r) {
        return new TileObject<>(r);
    }

    public RegistryElement<RECIPE> registry() {
        return registry;
    }

    public Item item() {
        return blockItem().asItem();
    }

    public BlockItem blockItem() {
        return registry().item().get();
    }

    public Block block() {
        return registry().block().get();
    }

    // public BlockEntityType<BE> be() {
    //     return (BlockEntityType<BE>) registry().be().get();
    // }

}
