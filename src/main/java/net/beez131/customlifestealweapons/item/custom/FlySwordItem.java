package net.beez131.customlifestealweapons.item.custom;

import net.beez131.customlifestealweapons.util.FlightTimerAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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

        if (!world.isClient()) {
            if (!user.getAbilities().allowFlying) {
                user.getAbilities().allowFlying = true;
                user.getAbilities().flying = true;
                user.sendAbilitiesUpdate();

                // Access the server and check for FlightTimerAccess
                if (world instanceof ServerWorld serverWorld) {
                    if (serverWorld instanceof FlightTimerAccess timerAccess) {
                        timerAccess.customlifestealweapons_template_1_21_1$setFlightTimer(user, 100L);
                    } else {
                        System.err.println("ServerWorld does not implement FlightTimerAccess!");
                    }
                } else {
                    System.err.println("World is not a ServerWorld!");
                }
            }

            user.getItemCooldownManager().set(this, 200);
            return TypedActionResult.success(stack);
        }

        return TypedActionResult.pass(stack);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {;
        tooltip.add(Text.translatable("tooltip.customlifestealweapons.slow_falling.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
