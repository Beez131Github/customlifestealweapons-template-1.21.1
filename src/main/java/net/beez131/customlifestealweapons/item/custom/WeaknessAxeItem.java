package net.beez131.customlifestealweapons.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class WeaknessAxeItem extends AxeItem {
    private static final int COOLDOWN_TICKS = 200; // 10 seconds cooldown (20 ticks per second)

    public WeaknessAxeItem(ToolMaterial material, Item.Settings settings) {
        super(material, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Check if the world is server-side and the attacker is a player
        if (!target.getWorld().isClient() && attacker instanceof PlayerEntity player) {
            // Check if the item is on cooldown
            if (!player.getItemCooldownManager().isCoolingDown(this)) {
                // Apply Weakness effect for 20 seconds (400 ticks)
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 400, 0));

                // Start cooldown for the item
                player.getItemCooldownManager().set(this, COOLDOWN_TICKS);
            }
        }
        return super.postHit(stack, target, attacker);
    }
}