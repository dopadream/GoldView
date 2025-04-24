package io.github.dopadream.goldview.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.dopadream.goldview.client.ViewmodelRenderState;
import io.github.dopadream.goldview.client.model.ViewModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public abstract class ViewmodelRenderer<T extends LivingEntity, S extends ViewmodelRenderState, M extends ViewModel<? super S>> extends LivingEntityRenderer<T, S, M> {
    public ViewmodelRenderer(EntityRendererProvider.Context context, M entityModel, float f) {
        super(context, entityModel, f);
    }

    @Override
    public void render(S livingEntityRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.pushPose();
        this.model.renderToBuffer(
                poseStack,
                multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(livingEntityRenderState))),
                i,
                OverlayTexture.NO_OVERLAY
        );

        M viewModel = this.getModel();
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(this.renderType());
        viewModel.setupAnim(livingEntityRenderState);
        viewModel.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(livingEntityRenderState, poseStack, multiBufferSource, i);
    }



    protected abstract RenderType renderType();

}
