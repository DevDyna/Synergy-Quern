package com.synergy.quern.init.builder.quern;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.synergy.quern.init.types.zBlocks;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class QuernRendering implements BlockEntityRenderer<QuernBE, QuernState> {
    private final BlockModelResolver brb;

    public QuernRendering(BlockEntityRendererProvider.Context context) {
        this.brb = context.blockModelResolver();
    }

    @Override
    public QuernState createRenderState() {
        return new QuernState();
    }

    @Override
    public void extractRenderState(QuernBE blockEntity, QuernState state, float partialTicks, Vec3 cameraPosition,
            ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        if (brb == null)
            return;

        if (state == null || state.block == null)
            return;

        this.brb.update(state.block, zBlocks.RENDER.get().defaultBlockState(), BlockDisplayContext.create());

        state.rotation = blockEntity.getRotation(partialTicks);
    }

    @Override
    public void submit(QuernState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector,
            CameraRenderState camera) {

        if (state == null || state.block == null)
            return;

        poseStack.pushPose();
        poseStack.translate(0.5, 0.3125, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.rotation));
        poseStack.translate(-0.5, -1, -0.5);
        state.block.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();

    }

}