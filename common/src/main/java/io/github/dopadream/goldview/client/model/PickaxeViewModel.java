package io.github.dopadream.goldview.client.model;

import io.github.dopadream.goldview.client.renderer.PickaxeViewModelRenderer;
import io.github.dopadream.goldview.client.viewmodel.ViewModel;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animatable.processing.AnimationTest;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class PickaxeViewModel extends ViewModel {

    public final PickaxeViewModelRenderer RENDERER = new PickaxeViewModelRenderer(this);


    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller", 20, (AnimationTest<PickaxeViewModel> state) -> {
            state.controller().setAnimation(RawAnimation.begin().thenLoop("animation.vpickaxe.idle"));
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public ResourceLocation getModelResource(GeoRenderState renderState) {
        return ResourceLocation.fromNamespaceAndPath("goldview", "object/vpickaxe");
    }

    @Override
    public ResourceLocation getTextureResource(GeoRenderState renderState) {
        return ResourceLocation.withDefaultNamespace("textures/item/diamond_pickaxe.png"); //test texture
    }

    @Override
    public ResourceLocation getAnimationResource(PickaxeViewModel animatable) {
        return ResourceLocation.fromNamespaceAndPath("goldview", "object/vpickaxe");
    }
}
