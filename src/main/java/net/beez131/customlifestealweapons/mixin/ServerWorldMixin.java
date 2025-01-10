package net.beez131.customlifestealweapons.mixin;

import net.beez131.customlifestealweapons.event.PlayerInventoryChecker;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        ServerWorld serverWorld = (ServerWorld) (Object) this;
        PlayerInventoryChecker.onTick(serverWorld);
    }
}