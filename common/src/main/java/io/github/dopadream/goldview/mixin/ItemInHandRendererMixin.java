package io.github.dopadream.goldview.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {

    @Shadow @Final private Minecraft minecraft;

    @Shadow protected abstract void renderPlayerArm(PoseStack arg, MultiBufferSource arg2, int i, float g, float h, HumanoidArm arg3);

    @Shadow @Final private EntityRenderDispatcher entityRenderDispatcher;

    @Shadow protected abstract void applyItemArmTransform(PoseStack arg, HumanoidArm arg2, float f);

    @Shadow public abstract void renderItem(LivingEntity arg, ItemStack arg2, ItemDisplayContext arg3, PoseStack arg4, MultiBufferSource arg5, int i);

    @Inject(method = "renderArmWithItem", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/HumanoidArm;RIGHT:Lnet/minecraft/world/entity/HumanoidArm;", ordinal = 1), cancellable = true)
    private void checkForItem(AbstractClientPlayer abstractClientPlayer,
                              float f, float g,
                              InteractionHand interactionHand,
                              float h,
                              ItemStack itemStack,
                              float i,
                              PoseStack poseStack,
                              MultiBufferSource multiBufferSource,
                              int j,
                              CallbackInfo ci)
    {
        boolean mainHand = interactionHand == InteractionHand.MAIN_HAND;
        HumanoidArm humanoidArm = mainHand ? abstractClientPlayer.getMainArm() : abstractClientPlayer.getMainArm().getOpposite();
        boolean isRightHanded = humanoidArm != HumanoidArm.LEFT;

        PlayerRenderer playerRenderer = (PlayerRenderer)this.entityRenderDispatcher.<AbstractClientPlayer>getRenderer(abstractClientPlayer);
        ResourceLocation resourceLocation = abstractClientPlayer.getSkin().texture();

        for (TagKey<Item> itemTagKey : itemStack.getTags().toList()) {
            switch (itemTagKey.location().getPath()) {
                case "pickaxes":
/*                    if (!Objects.requireNonNull(this.minecraft.player).isInvisible()) {
                        poseStack.pushPose();
                        this.applyItemArmTransform(poseStack, humanoidArm, i);
                        poseStack.mulPose(Axis.XP.rotationDegrees(-90));
                        poseStack.mulPose(Axis.YP.rotationDegrees(180));
                        if (isRightHanded) {
                            playerRenderer.renderRightHand(poseStack, multiBufferSource, j, resourceLocation, abstractClientPlayer.isModelPartShown(PlayerModelPart.RIGHT_SLEEVE));
                        } else {
                            playerRenderer.renderLeftHand(poseStack, multiBufferSource, j, resourceLocation, abstractClientPlayer.isModelPartShown(PlayerModelPart.LEFT_SLEEVE));
                        }
                        poseStack.popPose();


                    }

                    this.applyItemArmTransform(poseStack, humanoidArm, i);

                    poseStack.mulPose(Axis.YP.rotationDegrees(175));
                    poseStack.mulPose(Axis.XP.rotationDegrees(-20));

                    poseStack.translate(-0.25, -0.1, 0.65);
                    poseStack.scale(1.25f, 1.25f, 1.25f);

                    this.renderItem(
                            abstractClientPlayer,
                            itemStack,
                            !isRightHanded ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND,
                            poseStack,
                            multiBufferSource,
                            j
                    );

                    ci.cancel();*/

            }
        }
    }
}
