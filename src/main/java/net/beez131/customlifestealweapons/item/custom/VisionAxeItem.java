package net.beez131.customlifestealweapons.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class VisionAxeItem extends AxeItem {
    private static final int COOLDOWN_TICKS = 200; // 10 seconds cooldown (20 ticks per second)

    public VisionAxeItem(ToolMaterial material, Item.Settings settings) {
        super(material, settings); // Use only the material and settings as required
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Check if the world is server-side and the attacker is a player
        if (!target.getWorld().isClient() && attacker instanceof PlayerEntity player) {
            // Check if the item is on cooldown
            if (!player.getItemCooldownManager().isCoolingDown(this)) {
                // Apply Darkness effect for 1 minute (1200 ticks)
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 1200, 0));

                // Apply Glowing effect for 1 minute (1200 ticks)
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200, 0));

                // Start cooldown for the item
                player.getItemCooldownManager().set(this, COOLDOWN_TICKS);
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
