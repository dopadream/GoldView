package io.github.dopadream.goldview.neoforge;

import net.neoforged.fml.common.Mod;

import io.github.dopadream.goldview.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge() {
        // Run our common setup.
        ExampleMod.init();
    }
}
