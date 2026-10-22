package com.synergy.quern.init.builder.quern;

import com.devdyna.cakesticklib.api.client.utils.SimpleItemRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.synergy.quern.Config;
import com.synergy.quern.init.types.zBlocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;

public class QuernRendering implements BlockEntityRenderer<QuernBE, QuernState> {

    private final BlockModelResolver blockmodels;
    private final ItemModelResolver itemmodels;

    public QuernRendering(BlockEntityRendererProvider.Context ctx) {
        this.blockmodels = ctx.blockModelResolver();
        this.itemmodels = ctx.itemModelResolver();
    }

    @Override
    public QuernState createRenderState() {
        return new QuernState();
    }

    @Override
    public void extractRenderState(QuernBE be, QuernState state, float partialTicks, Vec3 cameraPosition,
            ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(be, state, partialTicks, cameraPosition, breakProgress);

        if (blockmodels == null)
            return;

        if (state == null || state.animation == null)
            return;

        blockmodels.update(state.animation, zBlocks.RENDER.get().defaultBlockState(), BlockDisplayContext.create());

        state.rotation = be.getRotation(partialTicks);

        if (!(be.getStackInSlot(0).isEmpty() || Config.DISABLE_ITEM_RENDER.get()))
            itemmodels.updateForTopItem(state.item, be.getStackInSlot(0), ItemDisplayContext.GROUND, be.getLevel(),
                    null, 0);

    }

    @Override
    public void submit(QuernState state, PoseStack stack, SubmitNodeCollector collector,
            CameraRenderState camera) {

        if (state == null)
            return;

        if (state.animation != null) {
            stack.pushPose();
            stack.translate(0.5, 0.5, 0.5);
            stack.mulPose(Axis.YP.rotationDegrees((float) state.rotation));
            stack.translate(-0.5, -0.5, -0.5);
            state.animation.submit(stack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            stack.popPose();
        }

        if (state.item != null)
            SimpleItemRender.of()
                    .move(0.5, 0.65, 0.5)
                    .rotateYN((Minecraft.getInstance().level.getGameTime() % 360) * 4f)
                    .scale(0.75f, 0.75f, 0.75f)
                    .build(state, state.item, stack, collector);

    }

}