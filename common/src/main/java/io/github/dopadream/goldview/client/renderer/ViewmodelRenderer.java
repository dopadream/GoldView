package io.github.dopadream.goldview.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public abstract class ViewmodelRenderer<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends LivingEntityRenderer<T, S, M> {
    public ViewmodelRenderer(EntityRendererProvider.Context context, M entityModel, float f) {
        super(context, entityModel, f);
    }
}
