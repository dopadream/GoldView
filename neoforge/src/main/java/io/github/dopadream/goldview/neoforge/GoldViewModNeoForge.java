package io.github.dopadream.goldview.neoforge;

import io.github.dopadream.goldview.client.GoldViewModClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

import io.github.dopadream.goldview.GoldViewMod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(GoldViewMod.MOD_ID)
public final class GoldViewModNeoForge {
    public GoldViewModNeoForge() {
        // Run our common setup.
        GoldViewMod.init();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            GoldViewModClient.init();
        }
    }
}
