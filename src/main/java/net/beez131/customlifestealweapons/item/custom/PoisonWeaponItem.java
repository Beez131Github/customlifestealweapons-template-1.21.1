package net.beez131.customlifestealweapons.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;

public class PoisonWeaponItem extends AxeItem {
    private static final int COOLDOWN_TICKS = 200; // 10 seconds cooldown (20 ticks per second)

    public PoisonWeaponItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Check if the world is server-side and the attacker is a player
        if (!target.getWorld().isClient() && attacker instanceof PlayerEntity player) {
            // Check if the item is on cooldown
            if (!player.getItemCooldownManager().isCoolingDown(this)) {
                // Apply poison effect to the target
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 1));

                // Start cooldown for the item
                player.getItemCooldownManager().set(this, COOLDOWN_TICKS);
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
