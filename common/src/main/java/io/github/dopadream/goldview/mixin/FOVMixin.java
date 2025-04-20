package io.github.dopadream.goldview.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class FOVMixin {

    @Shadow @Final private Minecraft minecraft;

    @Shadow public abstract float getDepthFar();


    @Inject(method = "getProjectionMatrix", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/GameRenderer;zoom:F", shift = At.Shift.AFTER), cancellable = true)
    private void overrideProjectionMatrix(float f, CallbackInfoReturnable<Matrix4f> cir, @Local Matrix4f matrix4f) {

        float aspect = (float)this.minecraft.getWindow().getWidth() / (float)this.minecraft.getWindow().getHeight();

        float verticalFovDegrees = goldfov$convertHorizontalFOVToVertical(f, aspect);
        float verticalFovRadians = (float)Math.toRadians(verticalFovDegrees);

        cir.setReturnValue(matrix4f.perspective(verticalFovRadians, aspect, 0.05F, this.getDepthFar()));
    }

    @Inject(method = "renderItemInHand", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;bobHurt(Lcom/mojang/blaze3d/vertex/PoseStack;F)V", shift = At.Shift.AFTER))
    private void pushHand(Camera camera, float f, Matrix4f matrix4f, CallbackInfo ci, @Local PoseStack poseStack) {

        float aspectRatio = (float)this.minecraft.getWindow().getWidth() / (float)this.minecraft.getWindow().getHeight();

        float baseAspect = 4.0f / 3.0f;

        poseStack.scale(1, 1, 1 / (baseAspect / aspectRatio));

    }

    @Unique
    float goldfov$convertHorizontalFOVToVertical(float horizontalFOVDegrees, float aspectRatio) {
        float hFovRad = (float)Math.toRadians(horizontalFOVDegrees);
        float baseAspect = 4.0f / 3.0f;
        float vFovRad = 2.0f * (float)Math.atan(Math.tan(hFovRad / 2.0f) / (aspectRatio / baseAspect));
        return (float)Math.toDegrees(vFovRad);
    }

}
