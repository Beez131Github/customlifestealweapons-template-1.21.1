package net.beez131.customlifestealweapons.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class VisionSwordItem extends SwordItem {
    public VisionSwordItem(ToolMaterial material, Item.Settings settings) {
        super(material, settings); // Use only the material and settings as required
    }

    @Override
    public boolean postHit(net.minecraft.item.ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient()) {
            // Remove old effects
            target.removeStatusEffect(StatusEffects.DARKNESS);
            target.removeStatusEffect(StatusEffects.GLOWING);

            // Apply Darkness effect for 1 minute (1200 ticks)
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 1200, 0));

            // Apply Glowing effect for 1 minute (1200 ticks)
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200, 0));
        }
        return super.postHit(stack, target, attacker);
    }
}
