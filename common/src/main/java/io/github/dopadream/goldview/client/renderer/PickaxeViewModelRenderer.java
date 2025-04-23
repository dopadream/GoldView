package io.github.dopadream.goldview.client.renderer;

import io.github.dopadream.goldview.client.GoldViewModClient;
import io.github.dopadream.goldview.client.ViewmodelRenderState;
import io.github.dopadream.goldview.client.model.viewmodels.PickaxeViewmodel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import static io.github.dopadream.goldview.GoldViewMod.MOD_ID;

public class PickaxeViewmodelRenderer extends LivingEntityRenderer<LivingEntity, LivingEntityRenderState, EntityModel<LivingEntityRenderState>> {
    public PickaxeViewmodelRenderer(EntityRendererProvider.Context context) {
        super(context,
                (EntityModel) new PickaxeViewmodel(context.bakeLayer(GoldViewModClient.PICKAXE_LAYER)),
                0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/viewmodel/iron_pickaxe.png");
    }


    @Override
    public @NotNull LivingEntityRenderState createRenderState() {
        return new ViewmodelRenderState();
    }

}
