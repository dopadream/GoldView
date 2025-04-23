package io.github.dopadream.goldview.client.renderer;

import io.github.dopadream.goldview.client.ViewmodelRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

@Environment(EnvType.CLIENT)
public class ViewmodelRendererManager {
    public static PickaxeViewmodelRenderer PICKAXE_RENDERER;
    public static ViewmodelRenderState PICKAXE_RENDERER_STATE;

    public static void init(EntityRendererProvider.Context context) {
        PICKAXE_RENDERER = new PickaxeViewmodelRenderer(context);
        PICKAXE_RENDERER_STATE = ViewmodelRendererManager.PICKAXE_RENDERER.createRenderState();
    }
}
