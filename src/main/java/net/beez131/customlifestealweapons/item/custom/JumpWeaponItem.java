package net.beez131.customlifestealweapons.item.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class JumpWeaponItem extends SwordItem {
    public JumpWeaponItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient()) {
            if (!user.getItemCooldownManager().isCoolingDown(this)) {
                // Check if the weapon is in the offhand or mainhand
                if (hand == Hand.MAIN_HAND) {
                    // Apply Jump Boost and Dolphin's Grace when held in the main hand
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 100, 2));  // Jump Boost
                } else if (hand == Hand.OFF_HAND) {
                    // Apply Levitation when held in the offhand
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 30, 19));  // Levitation
                }

                // Set cooldown (200 ticks = 10 seconds)
                user.getItemCooldownManager().set(this, 200);
            }
        }

        return TypedActionResult.success(stack, world.isClient());
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.customlifestealweapons.jump.tooltip"));
        tooltip.add(Text.translatable("tooltip.customlifestealweapons.featherstep.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
