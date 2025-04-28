package io.github.dopadream.goldview.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.dopadream.goldview.client.GoldViewModClient;
import io.github.dopadream.goldview.client.ViewmodelRenderState;
import io.github.dopadream.goldview.client.model.viewmodels.PickaxeViewmodel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import static io.github.dopadream.goldview.GoldViewMod.MOD_ID;

@Environment(EnvType.CLIENT)
public class PickaxeViewmodelRenderer extends ViewmodelRenderer<Player, ViewmodelRenderState, PickaxeViewmodel> {

    private final ResourceLocation pickaxeTexture;
    private ResourceLocation armsTexture;
    private final boolean slim;

    public PickaxeViewmodelRenderer(EntityRendererProvider.Context context) {
        super(context, createModel(context), 0.3F);

        this.pickaxeTexture = ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/viewmodel/iron_pickaxe.png");

        Minecraft minecraft = Minecraft.getInstance();
        AbstractClientPlayer player = minecraft.player != null ? minecraft.player : null;
        if (player != null) {
            this.armsTexture = player.getSkin().texture();
            this.slim = player.getSkin().model() == PlayerSkin.Model.SLIM;
        } else {
            this.armsTexture = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/player/wide/steve.png");
            this.slim = false;
        }
    }

    private static PickaxeViewmodel createModel(EntityRendererProvider.Context context) {
        ModelPart armsModelNormal = context.bakeLayer(GoldViewModClient.PICKAXE_LAYER);
        ModelPart armsModelSlim = context.bakeLayer(GoldViewModClient.PICKAXE_LAYER_SLIM);
        ModelPart pickaxeModel = context.bakeLayer(GoldViewModClient.PICKAXE_ITEM_LAYER);

        return new PickaxeViewmodel(armsModelNormal, armsModelSlim, pickaxeModel);
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
    public void render(ViewmodelRenderState state, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        // First, render arms with player skin texture
        VertexConsumer armsConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(armsTexture));
        Minecraft minecraft = Minecraft.getInstance();
        AbstractClientPlayer player = (AbstractClientPlayer) minecraft.cameraEntity;
        boolean slim = player != null && player.getSkin().model() == PlayerSkin.Model.SLIM;

        model.renderArmsOnly(poseStack, armsConsumer, light, slim);

        VertexConsumer pickaxeConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(pickaxeTexture));
        model.renderPickaxeOnly(poseStack, pickaxeConsumer, light);


        super.render(state, poseStack, bufferSource, light);

    }

    @Override
    protected RenderType renderType() {
        return RenderType.entityCutoutNoCull(this.pickaxeTexture);
    }

    @Override
    public void extractRenderState(Player livingEntity, ViewmodelRenderState livingEntityRenderState, float f) {
        super.extractRenderState(livingEntity, livingEntityRenderState, f);
    }
}
