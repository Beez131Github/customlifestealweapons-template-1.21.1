package net.beez131.customlifestealweapons;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.HashSet;
import java.util.Set;

public class VanillaAdvancementRewardHandler {
    private static final Set<ServerPlayerEntity> rewardedPlayers = new HashSet<>();

    private static void rewardPlayer(ServerPlayerEntity player) {
        player.giveItemStack(new net.minecraft.item.ItemStack(net.minecraft.item.Items.DIAMOND_SWORD));
        player.sendMessage(Text.literal("You have been rewarded for completing the 'Into Fire' advancement!").formatted(Formatting.GREEN), false);
    }
}
