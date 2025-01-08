package net.beez131.customlifestealweapons.event;

import net.beez131.customlifestealweapons.item.custom.PhobosAxeItem;
import net.beez131.customlifestealweapons.item.custom.TyphonSwordItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class PlayerInventoryChecker {

    public static void checkInventory(PlayerEntity player, World world) {
        boolean hasTyphonSword = false;
        boolean hasPhobosAxe = false;

        // Iterate through the player's inventory to check for both items
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() instanceof TyphonSwordItem) {
                hasTyphonSword = true;
            } else if (stack.getItem() instanceof PhobosAxeItem) {
                hasPhobosAxe = true;
            }

            // If both items are found, break early
            if (hasTyphonSword && hasPhobosAxe) {
                break;
            }
        }

        // If the player has both items, kill and explode them
        if (hasTyphonSword && hasPhobosAxe) {
            if (!world.isClient() && world instanceof ServerWorld serverWorld) {
                serverWorld.createExplosion(null, player.getX(), player.getY(), player.getZ(), 6.0f, true, ServerWorld.ExplosionSourceType.TNT);
            }
            player.kill(); // Kill the player
        }
    }
}
