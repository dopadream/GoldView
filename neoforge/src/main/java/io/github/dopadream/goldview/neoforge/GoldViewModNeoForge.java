package io.github.dopadream.goldview.neoforge;

import net.neoforged.fml.common.Mod;

import io.github.dopadream.goldview.GoldViewMod;

@Mod(GoldViewMod.MOD_ID)
public final class GoldViewModNeoForge {
    public GoldViewModNeoForge() {
        // Run our common setup.
        GoldViewMod.init();
    }
}
