package io.github.dopadream.goldview.client.animation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

@Environment(EnvType.CLIENT)
public class PickaxeViewmodelAnimation {
    public static final AnimationDefinition PICKAXE_IDLE = AnimationDefinition.Builder.withLength(12.0F)
            .addAnimation(
                    "r_bone",
                    new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -95.0F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.0F, KeyframeAnimations.degreeVec(-95.17F, 0.17F, -2.48F), AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(12.0F, KeyframeAnimations.degreeVec(-95F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
                    )
            )
            .build();


    public static void init() {

    }
}
