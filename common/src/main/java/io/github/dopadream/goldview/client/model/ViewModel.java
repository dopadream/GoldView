package io.github.dopadream.goldview.client.model;

import io.github.dopadream.goldview.client.model.PickaxeViewModel;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ViewModel extends GeoModel<ViewModel> implements GeoAnimatable {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object object) {
        assert Minecraft.getInstance().level != null;
        return Minecraft.getInstance().level.getGameTime();
    }

    @Override
    public ResourceLocation getModelResource(GeoRenderState renderState) {
        return null;
    }

    @Override
    public ResourceLocation getTextureResource(GeoRenderState renderState) {
        return null;
    }

    @Override
    public ResourceLocation getAnimationResource(ViewModel animatable) {
        return null;
    }
}

