package io.github.dopadream.goldview.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

import static net.minecraft.client.Options.genericValueLabel;

@Mixin(Options.class)
public abstract class OptionsMixin {

    @Mutable
    @Shadow @Final private OptionInstance<Integer> fov;

    @Inject(method = "<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Options;fov:Lnet/minecraft/client/OptionInstance;", shift = At.Shift.AFTER))
    private void overrideFOV(Minecraft minecraft, File file, CallbackInfo ci) {
        this.fov = new OptionInstance<>(
                "options.fov",
                OptionInstance.noTooltip(),
                (component, integer) -> switch (integer) {
                    case 90 -> genericValueLabel(component, Component.translatable("options.fov.min"));
                    case 120 -> genericValueLabel(component, Component.translatable("options.fov.max"));
                    default -> genericValueLabel(component, integer);
                },
                new OptionInstance.IntRange(60, 120),
                Codec.DOUBLE.xmap(double_ -> (int)(double_ * 40.0 + 70.0), integer -> (integer - 70.0) / 40.0),
                90,
                integer -> Minecraft.getInstance().levelRenderer.needsUpdate()
        );
    }
}
