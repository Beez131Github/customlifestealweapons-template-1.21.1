package net.beez131.customlifestealweapons.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class WeaknessAxeItem extends AxeItem {
    public WeaknessAxeItem(ToolMaterial material, Item.Settings settings) {
        super(material, settings);
    }

    @Override
    public boolean postHit(net.minecraft.item.ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient()) {
            // Remove the old Weakness effect if it exists
            target.removeStatusEffect(StatusEffects.WEAKNESS);

            // Apply Weakness effect for 20 seconds (400 ticks)
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 400, 0));
        }
        return super.postHit(stack, target, attacker);
    }
}
