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
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.List;

public class AetherWaveSwordItem extends SwordItem {

    public AetherWaveSwordItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient()) {
            if (!user.getItemCooldownManager().isCoolingDown(this)) {
                if (hand == Hand.MAIN_HAND) {
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, 9));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 100, 2));
                } else if (hand == Hand.OFF_HAND) {
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 100, 0));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 30, 19));
                }

                user.getItemCooldownManager().set(this, 160);
            }
        }

        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {;
        tooltip.add(Text.translatable("tooltip.customlifestealweapons.speed.tooltip"));
        tooltip.add(Text.translatable("tooltip.customlifestealweapons.jump.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}