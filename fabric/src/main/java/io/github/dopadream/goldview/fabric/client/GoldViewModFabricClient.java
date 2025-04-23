package io.github.dopadream.goldview.fabric.client;

import io.github.dopadream.goldview.client.GoldViewModClient;
import io.github.dopadream.goldview.client.model.viewmodels.PickaxeViewmodel;
import io.github.dopadream.goldview.client.renderer.PickaxeViewmodelRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.world.entity.EntityType;

public final class GoldViewModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        GoldViewModClient.init();

        EntityRendererRegistry.register(EntityType.PLAYER, PickaxeViewmodelRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(GoldViewModClient.PICKAXE_LAYER, PickaxeViewmodel::createLayer);
    }
}
