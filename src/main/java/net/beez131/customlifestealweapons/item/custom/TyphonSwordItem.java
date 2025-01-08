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
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.List;

public class TyphonSwordItem extends SwordItem {

    public TyphonSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient()) {

            // Apply Speed effect if held in main hand
            if (hand == Hand.MAIN_HAND) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, 9));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 100, 2));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 4));
            }

            // Apply Dolphin's Grace effect if held in offhand
            if (hand == Hand.OFF_HAND) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 100, 0));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 30, 19));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200, 0));
            }

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

            user.getItemCooldownManager().set(this, 100);
            return TypedActionResult.success(stack);
        }

        return TypedActionResult.pass(stack);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {;
        tooltip.add(Text.translatable("tooltip.customlifestealweapons.slow_falling.tooltip"));
        tooltip.add(Text.translatable("tooltip.customlifestealweapons.jump.tooltip"));
        tooltip.add(Text.translatable("tooltip.customlifestealweapons.featherstep.tooltip"));
        tooltip.add(Text.translatable("tooltip.customlifestealweapons.resistance.tooltip"));
        tooltip.add(Text.translatable("tooltip.customlifestealweapons.speed.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
