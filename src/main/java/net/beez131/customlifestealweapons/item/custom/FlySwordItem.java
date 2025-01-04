package net.beez131.customlifestealweapons.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FlySwordItem extends SwordItem {

    public FlySwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient()) {
            // Enable flying if not on cooldown
            if (!user.getItemCooldownManager().isCoolingDown(this)) {
                user.getAbilities().allowFlying = true;
                user.getAbilities().flying = true;
                user.sendAbilitiesUpdate();

                // Schedule disabling flight after 5 seconds (100 ticks)
                world.getServer().getOverworld().getServer().execute(() -> {
                    try {
                        Thread.sleep(5000); // 5 seconds
                        user.getAbilities().allowFlying = false;
                        user.getAbilities().flying = false;
                        user.sendAbilitiesUpdate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                // Set a cooldown of 10 seconds (200 ticks)
                user.getItemCooldownManager().set(this, 100);
            }
        }

        return TypedActionResult.success(stack, world.isClient());
    }
}