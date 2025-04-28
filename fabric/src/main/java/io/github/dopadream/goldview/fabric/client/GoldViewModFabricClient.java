package io.github.dopadream.goldview.fabric.client;

import io.github.dopadream.goldview.client.GoldViewModClient;
import io.github.dopadream.goldview.client.model.viewmodels.PickaxeViewmodel;
import io.github.dopadream.goldview.client.renderer.PickaxeViewmodelRenderer;
import io.github.dopadream.goldview.client.renderer.ViewmodelRenderer;
import io.github.dopadream.goldview.client.renderer.ViewmodelRendererManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntityType;

public final class GoldViewModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        GoldViewModClient.init();

        EntityRendererRegistry.register(EntityType.PLAYER, ctx -> {
            ViewmodelRendererManager.init(ctx);
            return new PickaxeViewmodelRenderer(ctx);
        });

        EntityModelLayerRegistry.registerModelLayer(GoldViewModClient.PICKAXE_ITEM_LAYER, PickaxeViewmodel::createPickaxeLayer);
        EntityModelLayerRegistry.registerModelLayer(GoldViewModClient.PICKAXE_LAYER, PickaxeViewmodel::createLayer);
        EntityModelLayerRegistry.registerModelLayer(GoldViewModClient.PICKAXE_LAYER_SLIM, PickaxeViewmodel::createSlimLayer);

    }
}
