package net.beez131.customlifestealweapons.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class FlySwordItem extends SwordItem {

    public FlySwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // Server-side logic (flight effect can only be granted on the server)
        if (!world.isClient()) {
            // Check if the sword is not on cooldown
            if (!user.getItemCooldownManager().isCoolingDown(this)) {
                System.out.println("Granting flight for: " + user.getName().getString());

                // Enable flight for the player
                user.getAbilities().allowFlying = true;
                user.getAbilities().flying = true;

                // Update abilities to make sure the client knows the player is allowed to fly
                user.sendAbilitiesUpdate();
                System.out.println("Flight enabled for: " + user.getName().getString());

                // Disable flight after 5 seconds (100 ticks) using server-side task
                world.getServer().execute(() -> {
                    // Disable flying after the delay (100 ticks, 5 seconds)
                    user.getAbilities().allowFlying = false;
                    user.getAbilities().flying = false;

                    // Update abilities again to reflect the change on the client side
                    user.sendAbilitiesUpdate();
                    System.out.println("Flight disabled for: " + user.getName().getString());
                });

                // Set the cooldown to 10 seconds (200 ticks)
                user.getItemCooldownManager().set(this, 200);
                System.out.println("Cooldown set for: " + user.getName().getString());
            }
        } else {
            // Client-side logic: Force cooldown on the client to prevent spamming right-click
            if (!user.getItemCooldownManager().isCoolingDown(this)) {
                user.getItemCooldownManager().set(this, 200);
            }
        }

        // Return the success action result and whether it's a client-side action
        return TypedActionResult.success(stack, world.isClient());
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {;
        tooltip.add(Text.translatable("tooltip.customlifestealweapons.slow_falling.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
