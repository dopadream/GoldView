package io.github.dopadream.goldview.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Pose;

@Environment(EnvType.CLIENT)
public class ViewmodelRenderState extends LivingEntityRenderState {

    public float ageInTicks;
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState swapAnimationState = new AnimationState();
    public final AnimationState swingAnimationState = new AnimationState();
    public Pose pose = Pose.STANDING;


    public boolean hasPose(Pose pose) {
        return this.pose == pose;
    }

}
