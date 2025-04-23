package io.github.dopadream.goldview.client.model;

import io.github.dopadream.goldview.client.ViewmodelRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public abstract class ViewModel<T extends ViewmodelRenderState> extends EntityModel<T> {

    protected ViewModel(ModelPart modelPart) {
        this(modelPart, RenderType::itemEntityTranslucentCull);
    }

    protected ViewModel(ModelPart modelPart, Function<ResourceLocation, RenderType> function) {
        super(modelPart, function);
    }

}
