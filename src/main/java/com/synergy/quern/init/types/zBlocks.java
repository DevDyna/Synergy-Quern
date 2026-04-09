package com.synergy.quern.init.types;

import static com.synergy.quern.Main.ID;

import com.synergy.quern.api.TileObject;
import com.synergy.quern.init.Material;
import com.synergy.quern.init.builder.quern.*;
import com.synergy.quern.init.builder.quern.recipe.MillingRecipe;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zBlocks {

    public static void register(IEventBus bus) {
        zBlock.register(bus);
        zBlockItem.register(bus);
    }

    public static final DeferredRegister.Blocks zBlock = DeferredRegister.createBlocks(ID);
    public static final DeferredRegister.Blocks zBlockItem = DeferredRegister.createBlocks(ID);

    public static final DeferredHolder<Block, Block> QUERN = Material.registerItemBlock("quern",
            () -> new QuernBlock());

    public static final DeferredHolder<Block, Block> RENDER = zBlock.register("render",
            () -> new RenderBlock());

}
