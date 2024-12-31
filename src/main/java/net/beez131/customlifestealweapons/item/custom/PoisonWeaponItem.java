package net.beez131.customlifestealweapons.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;

public class PoisonWeaponItem extends AxeItem {
    public PoisonWeaponItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient()) {
            // Get current poison effect (if any)
            StatusEffectInstance currentPoison = target.getStatusEffect(StatusEffects.POISON);
            int amplifier = 0;

            if (currentPoison != null) {
                // Increase amplifier if poison already exists
                amplifier = currentPoison.getAmplifier() + 1;
            }

            // Apply poison effect with updated amplifier
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, amplifier));
        }
        return super.postHit(stack, target, attacker);
    }
}