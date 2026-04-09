package com.synergy.quern.init.builder.quern;

import com.synergy.quern.init.types.zBlocks;

import net.minecraft.world.level.block.Block;

public class RenderBlock extends Block{

    public RenderBlock() {
        super(Properties.of().setId(zBlocks.RENDER.getKey()).noLootTable());
    }
    
}
