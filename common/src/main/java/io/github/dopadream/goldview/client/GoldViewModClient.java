package io.github.dopadream.goldview.client;

import io.github.dopadream.goldview.client.animation.PickaxeViewmodelAnimation;
import io.github.dopadream.goldview.client.model.viewmodels.PickaxeViewmodel;
import io.github.dopadream.goldview.client.renderer.PickaxeViewmodelRenderer;
import io.github.dopadream.goldview.client.renderer.ViewmodelRenderer;
import io.github.dopadream.goldview.mixin.ModelLayersAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import static io.github.dopadream.goldview.GoldViewMod.MOD_ID;

@Environment(EnvType.CLIENT)
public class GoldViewModClient {
    public static final ModelLayerLocation PICKAXE_LAYER = register("v_pickaxe");
    public static final ModelLayerLocation PICKAXE_LAYER_SLIM = register("v_pickaxe_s");
    public static final ModelLayerLocation PICKAXE_ITEM_LAYER = register("v_pickaxe_item");



    public static void init(){
        PickaxeViewmodelAnimation.init();
    }

    private static ModelLayerLocation register(String string) {
        return register(string, "main");
    }

    private static ModelLayerLocation register(String string, String string2) {
        ModelLayerLocation modelLayerLocation = createLocation(string, string2);
        if (!ModelLayersAccessor.getModels().add(modelLayerLocation)) {
            throw new IllegalStateException("Duplicate registration for " + modelLayerLocation);
        } else {
            return modelLayerLocation;
        }
    }

    private static ModelLayerLocation createLocation(String string, String string2) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MOD_ID, string), string2);
    }
}
