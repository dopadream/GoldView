package io.github.dopadream.goldview.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.dopadream.goldview.client.model.PickaxeViewModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoObjectRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class PickaxeViewModelRenderer extends GeoObjectRenderer<PickaxeViewModel> {

    public PickaxeViewModelRenderer(GeoModel<PickaxeViewModel> model)
    {
        super(model);
    }

    @Override
    public @Nullable RenderType getRenderType(GeoRenderState renderState, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }

    @Override
    public long getInstanceId(PickaxeViewModel animatable, Void relatedObject) {
        return animatable.hashCode();
    }

    @Override
    public void render(PoseStack poseStack, PickaxeViewModel animatable, @Nullable MultiBufferSource bufferSource, @Nullable RenderType renderType, @Nullable VertexConsumer buffer, int packedLight, float partialTick) {
        super.render(poseStack, animatable, bufferSource, renderType, buffer, packedLight, partialTick);
    }
}
