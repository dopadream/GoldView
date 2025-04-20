package io.github.dopadream.goldview.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.dopadream.goldview.client.renderer.PickaxeViewModelRenderer;
import io.github.dopadream.goldview.client.model.PickaxeViewModel;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;


@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Final private EntityRenderDispatcher entityRenderDispatcher;


    @Shadow protected abstract void applyItemArmTransform(PoseStack arg, HumanoidArm arg2, float f);

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


        for (TagKey<Item> itemTagKey : itemStack.getTags().toList()) {
            switch (itemTagKey.location().getPath()) {
                case "pickaxes":
                   if (abstractClientPlayer.isInvisible()) {

                       PickaxeViewModel viewModel = new PickaxeViewModel();
                       PickaxeViewModelRenderer renderer = viewModel.RENDERER;
                       poseStack.pushPose();
                       // renders invisible right now? not sure why
                       renderer.render(poseStack, viewModel, multiBufferSource, null, null, Minecraft.getInstance().level.getLightEmission(abstractClientPlayer.blockPosition()), minecraft.getFrameTimeNs());
                       poseStack.popPose();
                   }
                   ci.cancel();
            }
        }
    }
}
