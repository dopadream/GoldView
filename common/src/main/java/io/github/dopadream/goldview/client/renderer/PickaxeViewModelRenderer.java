package io.github.dopadream.goldview.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.dopadream.goldview.client.GoldViewModClient;
import io.github.dopadream.goldview.client.ViewmodelRenderState;
import io.github.dopadream.goldview.client.model.viewmodels.PickaxeViewmodel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import static io.github.dopadream.goldview.GoldViewMod.MOD_ID;

@Environment(EnvType.CLIENT)
public class PickaxeViewmodelRenderer extends ViewmodelRenderer<Player, ViewmodelRenderState, PickaxeViewmodel> {

    private final ResourceLocation pickaxeTexture;
    private ResourceLocation armsTexture;

    public PickaxeViewmodelRenderer(EntityRendererProvider.Context context) {
        super(context,
                new PickaxeViewmodel(context.bakeLayer(GoldViewModClient.PICKAXE_LAYER)),
                0.3F);
        this.pickaxeTexture = ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/viewmodel/iron_pickaxe.png");
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ViewmodelRenderState state) {
        // Required by super, but we won't use it directly in renderType()
        return this.pickaxeTexture;
    }

    @Override
    public @NotNull ViewmodelRenderState createRenderState() {
        return new ViewmodelRenderState();
    }

    @Override
    public void render(ViewmodelRenderState livingEntityRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        // Setup animations etc.
        this.model.setupAnim(livingEntityRenderState);
        this.armsTexture = Minecraft.getInstance().player != null ? Minecraft.getInstance().player.getSkin().texture() : null;

        // First pass: arms
        RenderType armsRenderType = null;
        if (this.armsTexture != null) {
            armsRenderType = RenderType.entityCutoutNoCull(this.armsTexture);
            this.model.renderArmsOnly(poseStack, multiBufferSource.getBuffer(armsRenderType), i);
        }

        super.render(livingEntityRenderState, poseStack, multiBufferSource, i);
    }

    @Override
    protected RenderType renderType() {
        return RenderType.entityCutoutNoCull(this.pickaxeTexture);
    }
}
