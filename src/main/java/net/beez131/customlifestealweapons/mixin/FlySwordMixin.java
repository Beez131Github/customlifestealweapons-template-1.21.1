package net.beez131.customlifestealweapons.mixin;

import net.beez131.customlifestealweapons.util.FlightTimerAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(ServerWorld.class)
public abstract class FlySwordMixin implements FlightTimerAccess {
    @Unique
    private final Map<PlayerEntity, Long> flightTimers = new HashMap<>();

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        flightTimers.entrySet().removeIf(entry -> {
            PlayerEntity player = entry.getKey();
            long remainingTicks = entry.getValue() - 1;

            if (remainingTicks <= 0) {
                // Disable flying when timer expires
                if (player.getAbilities().allowFlying) {
                    player.getAbilities().allowFlying = false;
                    player.getAbilities().flying = false;
                    player.sendAbilitiesUpdate();
                    System.out.println("Flying disabled for " + player.getName().getString());
                }
                return true; // Remove from map
            }

            // Update remaining ticks
            entry.setValue(remainingTicks);
            return false;
        });
    }


    @Override
    public void customlifestealweapons_template_1_21_1$setFlightTimer(PlayerEntity player, long ticks) {
        // Debug: Log setting the timer
        System.out.println("Set flight timer for " + player.getName().getString() + " with " + ticks + " ticks.");

        flightTimers.put(player, ticks);
        player.getAbilities().allowFlying = true;
        player.getAbilities().flying = true;
        player.sendAbilitiesUpdate();
    }
}
