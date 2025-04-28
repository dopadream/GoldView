package io.github.dopadream.goldview.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.dopadream.goldview.client.renderer.ViewmodelRendererManager;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {


    @Shadow protected abstract void applyItemArmTransform(PoseStack poseStack, HumanoidArm humanoidArm, float f);

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
        int q = (humanoidArm == HumanoidArm.RIGHT) ? 1 : -1;


        for (TagKey<Item> itemTagKey : itemStack.getTags().toList()) {
            switch (itemTagKey.location().getPath()) {
                case "pickaxes":
                   if (!abstractClientPlayer.isInvisible()) {
                       poseStack.pushPose();
                       // render item here
                       poseStack.mulPose(Axis.ZP.rotationDegrees(180));
                       ViewmodelRendererManager.PICKAXE_RENDERER.render(ViewmodelRendererManager.PICKAXE_RENDERER_STATE, poseStack, multiBufferSource, j);
                       ViewmodelRendererManager.PICKAXE_RENDERER_STATE.ageInTicks = abstractClientPlayer.tickCount;
                       poseStack.popPose();
                   }
                   ci.cancel();
            }
        }
    }
}
