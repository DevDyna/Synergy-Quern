package com.synergy.quern.init;

import static com.synergy.quern.Main.ID;

import java.util.*;
import java.util.function.*;

import com.synergy.quern.init.types.*;
import com.synergy.quern.utils.x;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class Material {
        public static void register(IEventBus bus) {
                zItems.register(bus);
                zBlocks.register(bus);
                zBlockEntities.register(bus);
                zRecipeTypes.register(bus);
                zHandlers.register(bus);
        }

        /**
         * register an block + item
         * 
         * @param sup () -> new Builder
         */
        public static DeferredHolder<Block, Block> registerItemBlock(String blockname, Supplier<Block> sup) {
                return registerItemBlock(blockname, sup, zBlocks.zBlockItem);
        }

        /**
         * register an block + item
         * 
         * @param sup () -> new Builder
         * @param b   Blocks.zBlock
         */
        public static DeferredHolder<Block, Block> registerItemBlock(String blockname, Supplier<Block> sup,
                        DeferredRegister.Blocks b) {
                DeferredHolder<Block, Block> block = b.register(blockname, sup);
                zItems.zBlockItem.registerSimpleBlockItem(block);
                return block;
        }

        public static DeferredHolder<Block, Block> registerItemBlock(String blockname, DeferredRegister.Blocks b) {
                return registerItemBlock(blockname, () -> new Block(BlockBehaviour.Properties.of()), b);
        }

        /**
         * create an itemtag
         */
        public static TagKey<Item> tagItem(String name) {
                return TagKey.create(BuiltInRegistries.ITEM.key(),
                                x.rl(ID, name));
        }

        /**
         * create an blocktag
         */
        public static TagKey<Block> tagBlock(String name) {
                return TagKey.create(BuiltInRegistries.BLOCK.key(),
                                x.rl(ID, name));
        }

        /**
         * create an itemtag
         */
        public static TagKey<Item> tagItem(String name, String modname) {
                return TagKey.create(BuiltInRegistries.ITEM.key(),
                                x.rl(modname, name));
        }

        /**
         * create an blocktag
         */
        public static TagKey<Block> tagBlock(String name, String modname) {
                return TagKey.create(BuiltInRegistries.BLOCK.key(),
                                x.rl(modname, name));
        }

        /**
         * create an fluidtag
         */
        public static TagKey<Fluid> tagFluid(String name) {
                return TagKey.create(BuiltInRegistries.FLUID.key(),
                                x.rl(ID, name));
        }

        /**
         * create an fluidtag
         */
        public static TagKey<Fluid> tagFluid(String name, String modname) {
                return TagKey.create(BuiltInRegistries.FLUID.key(),
                                x.rl(modname, name));
        }

        /**
         * create an itemtag
         */
        public static TagKey<EntityType<?>> tagEntity(String name) {
                return tagEntity(ID, name);
        }

        /**
         * create an biome tag
         */
        public static TagKey<Biome> tagBiome(String name) {
                return TagKey.create(Registries.BIOME, x.rl(ID, name));
        }

        /**
         * create an entity tag
         */
        public static TagKey<EntityType<?>> tagEntity(String modname, String name) {
                return TagKey.create(Registries.ENTITY_TYPE, x.rl(modname, name));
        }

        public static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> createBlockEntity(
                        String name,
                        BlockEntitySupplier<T> factory,
                        Supplier<? extends Block>... validBlocks) {
                return zBlockEntities.zTiles.register(name,
                                () -> new BlockEntityType<>(factory,
                                                Arrays.stream(validBlocks).map(Supplier::get).toArray(Block[]::new)));
        }

        public static ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeature(String i) {
                return ResourceKey.create(Registries.CONFIGURED_FEATURE, x.rl(i));
        }

        public static ResourceKey<PlacedFeature> createPlacedFeature(String i) {
                return ResourceKey.create(Registries.PLACED_FEATURE, x.rl(i));
        }

        public static ResourceKey<BiomeModifier> createBiomeModifier(String i) {
                return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, x.rl(i));
        }

}
