package io.github.dopadream.goldview.client.model;

import io.github.dopadream.goldview.client.ViewmodelRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class ViewModel<T extends ViewmodelRenderState> extends EntityModel<T> {

    protected ViewModel(ModelPart modelPart) {
        this(modelPart, RenderType::entityCutoutNoCull);
    }

    protected ViewModel(ModelPart modelPart, Function<ResourceLocation, RenderType> function) {
        super(modelPart, function);
    }

    public void setupAnim(T entityRenderState) {
        this.resetPose();
    }
}
