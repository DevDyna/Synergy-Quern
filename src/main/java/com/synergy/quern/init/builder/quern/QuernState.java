package com.synergy.quern.init.builder.quern;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class QuernState extends BlockEntityRenderState {
    public BlockModelRenderState animation = new BlockModelRenderState();
    public ItemStackRenderState item = new ItemStackRenderState();
    public double rotation;

}
