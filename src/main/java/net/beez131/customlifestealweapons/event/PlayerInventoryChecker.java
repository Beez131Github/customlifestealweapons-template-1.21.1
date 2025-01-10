package net.beez131.customlifestealweapons.event;

import net.beez131.customlifestealweapons.item.custom.PhobosAxeItem;
import net.beez131.customlifestealweapons.item.custom.TyphonSwordItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerInventoryChecker {
    private static final Map<UUID, Long> explosionTimers = new HashMap<>();
    private static final Map<UUID, Long> gradientTimers = new HashMap<>();

    public static void checkInventory(PlayerEntity player, World world) {
        boolean hasTyphonSword = false;
        boolean hasPhobosAxe = false;

        // Check if the player has the required items
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() instanceof TyphonSwordItem) {
                hasTyphonSword = true;
            } else if (stack.getItem() instanceof PhobosAxeItem) {
                hasPhobosAxe = true;
            }

            if (hasTyphonSword && hasPhobosAxe) {
                break;
            }
        }

        // Manage timers based on item presence
        if (hasTyphonSword && hasPhobosAxe) {
            explosionTimers.putIfAbsent(player.getUuid(), 60L); // 3 seconds
            gradientTimers.put(player.getUuid(), 3L); // Apply gradient for 3 ticks
        } else {
            explosionTimers.remove(player.getUuid());
            gradientTimers.remove(player.getUuid());
        }
    }

    public static void onServerTick(MinecraftServer server) {
        System.out.println("Running global onServerTick...");

        // Process explosion timers
        explosionTimers.entrySet().removeIf(entry -> {
            UUID playerId = entry.getKey();
            PlayerEntity player = server.getPlayerManager().getPlayer(playerId);

            if (player == null || !player.isAlive()) {
                System.out.println("Player not found or dead. Removing explosion timer for UUID: " + playerId);
                return true; // Remove timer
            }

            long remainingTicks = entry.getValue() - 1;

            if (remainingTicks <= 0) {
                System.out.println("Triggering explosion for player: " + player.getName().getString());
                player.getWorld().createExplosion(
                        null, player.getX(), player.getY(), player.getZ(),
                        6.0f, true, ServerWorld.ExplosionSourceType.TNT
                );
                player.kill();
                return true; // Remove timer
            }

            entry.setValue(remainingTicks);
            return false;
        });

        // Process gradient timers
        gradientTimers.entrySet().removeIf(entry -> {
            UUID playerId = entry.getKey();
            PlayerEntity player = server.getPlayerManager().getPlayer(playerId);

            if (player == null || !player.isAlive()) {
                System.out.println("Player not found or dead. Removing gradient timer for UUID: " + playerId);
                return true; // Remove timer
            }

            long remainingTicks = entry.getValue() - 1;

            if (remainingTicks <= 0) {
                System.out.println("Gradient expired for player: " + player.getName().getString());
                return true; // Remove timer
            }

            // Apply gradient effect here
            System.out.println("Applying gradient to player: " + player.getName().getString());
            entry.setValue(remainingTicks);
            return false;
        });
    }
}
