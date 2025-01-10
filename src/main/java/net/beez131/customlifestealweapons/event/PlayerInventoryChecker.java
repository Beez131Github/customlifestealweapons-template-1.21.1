package net.beez131.customlifestealweapons.event;

import net.beez131.customlifestealweapons.item.custom.PhobosAxeItem;
import net.beez131.customlifestealweapons.item.custom.TyphonSwordItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerInventoryChecker {
    private static final Map<UUID, Long> explosionTimers = new HashMap<>();
    private static final Map<UUID, Integer> gradientTimers = new HashMap<>();

    public static void checkInventory(PlayerEntity player, World world) {
        boolean hasTyphonSword = false;
        boolean hasPhobosAxe = false;

        System.out.println("Checking inventory for player: " + player.getName().getString());

        // Iterate through the player's inventory to check for both items
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() instanceof TyphonSwordItem) {
                hasTyphonSword = true;
                System.out.println("Player has Typhon Sword.");
            } else if (stack.getItem() instanceof PhobosAxeItem) {
                hasPhobosAxe = true;
                System.out.println("Player has Phobos Axe.");
            }

            // If both items are found, break early
            if (hasTyphonSword && hasPhobosAxe) {
                System.out.println("Player has both items.");
                break;
            }
        }

        UUID playerId = player.getUuid();

        // If the player has both items, start the explosion timer
        if (hasTyphonSword && hasPhobosAxe) {
            if (!world.isClient() && world instanceof ServerWorld) {
                explosionTimers.putIfAbsent(playerId, 60L);
                gradientTimers.put(playerId, 3); // Reset gradient timer to 3 ticks
                System.out.println("Started explosion and gradient timers for player: " + player.getName().getString());
            }
        } else {
            // If the player no longer has both items, cancel timers
            explosionTimers.remove(playerId);
            System.out.println("Removed explosion timer for player: " + player.getName().getString());
        }
    }

    public static void onTick(ServerWorld serverWorld) {
        System.out.println("Running onTick...");

        // Process explosion timers
        explosionTimers.entrySet().removeIf(entry -> {
            UUID playerId = entry.getKey();
            PlayerEntity player = serverWorld.getPlayerByUuid(playerId);

            if (player == null) {
                System.out.println("Player not found for explosion timer. Removing entry.");
                return true;
            }

            long remainingTicks = entry.getValue() - 1;
            System.out.println("Explosion timer for player " + player.getName().getString() + ": " + remainingTicks + " ticks remaining.");

            if (remainingTicks <= 0) {
                System.out.println("Explosion timer expired for player: " + player.getName().getString());
                // Trigger explosion and kill the player when timer expires
                serverWorld.createExplosion(
                        player,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        6.0f,
                        true,
                        ServerWorld.ExplosionSourceType.TNT
                );
                player.kill(); // Kill the player
                System.out.println("Explosion triggered and player killed: " + player.getName().getString());
                return true; // Remove timer
            }

            // Update remaining ticks
            entry.setValue(remainingTicks);
            return false;
        });

        // Process gradient timers
        gradientTimers.entrySet().removeIf(entry -> {
            UUID playerId = entry.getKey();
            PlayerEntity player = serverWorld.getPlayerByUuid(playerId);

            if (player == null || !player.isAlive()) {
                System.out.println("Player not found or dead for gradient timer. Removing entry.");
                return true;
            }

            // Apply gradient effect
            applyGradientEffect(player);
            System.out.println("Applied gradient effect to player: " + player.getName().getString());

            int remainingTicks = entry.getValue() - 1;
            System.out.println("Gradient timer for player " + player.getName().getString() + ": " + remainingTicks + " ticks remaining.");

            if (remainingTicks <= 0) {
                // Remove gradient effect if timer expires
                removeGradientEffect(player);
                System.out.println("Removed gradient effect from player: " + player.getName().getString());
                return true;
            }

            // Update remaining ticks
            entry.setValue(remainingTicks);
            return false;
        });
    }

    private static void applyGradientEffect(PlayerEntity player) {
        // Your logic to apply the gradient effect to the player
        System.out.println("Applying gradient effect to player: " + player.getName().getString());
    }

    private static void removeGradientEffect(PlayerEntity player) {
        // Your logic to remove the gradient effect from the player
        System.out.println("Removing gradient effect from player: " + player.getName().getString());
    }
}
