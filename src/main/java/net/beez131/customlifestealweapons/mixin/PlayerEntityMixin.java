package net.beez131.customlifestealweapons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.beez131.customlifestealweapons.item.custom.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void applySpeedEffect(CallbackInfo ci) {
        // Ensure the entity is a player
        if ((Object) this instanceof PlayerEntity player) {
            // Check if the player is holding the SpeedSwordItem
            ItemStack mainHand = player.getMainHandStack();
            ItemStack offHand = player.getOffHandStack();

            boolean isHoldingSpeedSword = mainHand.getItem() instanceof SpeedSwordItem
                    || offHand.getItem() instanceof SpeedSwordItem
                    || mainHand.getItem() instanceof AetherWaveSwordItem
                    || offHand.getItem() instanceof AetherWaveSwordItem;

            if (isHoldingSpeedSword) {
                // Apply Speed I effect (duration = 2 seconds, refreshes every tick)
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2, 0, false, true));
            }
        }
    }
    @Inject(method = "tick", at = @At("HEAD"))
    private void applyResistanceEffect(CallbackInfo ci) {
        // Ensure the entity is a player
        if ((Object) this instanceof PlayerEntity player) {
            // Check if the player is holding the SpeedSwordItem
            ItemStack mainHand = player.getMainHandStack();
            ItemStack offHand = player.getOffHandStack();

            boolean isHoldingResistanceSword = mainHand.getItem() instanceof ResistanceSwordItem
                    || offHand.getItem() instanceof ResistanceSwordItem;

            if (isHoldingResistanceSword) {
                // Apply Speed I effect (duration = 2 seconds, refreshes every tick)
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2, 0, false, true));
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void applySlowFallingEffect(CallbackInfo ci) {
        // Ensure the entity is a player
        if ((Object) this instanceof PlayerEntity player) {
            // Check if the player is holding the SpeedSwordItem
            ItemStack mainHand = player.getMainHandStack();
            ItemStack offHand = player.getOffHandStack();

            boolean isHoldingSlowFallingSword = mainHand.getItem() instanceof FlySwordItem
                    || offHand.getItem() instanceof FlySwordItem;

            if (isHoldingSlowFallingSword) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 2, 0, false, true));
            }
        }
    }

    @ModifyReturnValue(
            method = "getStepHeight",
            at = @At("RETURN")
    )
    protected float modifyStepHeight(float original) {
        // Ensure the entity is a player
        if ((Object) this instanceof PlayerEntity player) {
            // Check if the player is holding AetherWaveSwordItem or JumpWeaponItem
            ItemStack mainHand = player.getMainHandStack();
            ItemStack offHand = player.getOffHandStack();

            boolean isHoldingValidWeapon = mainHand.getItem() instanceof AetherWaveSwordItem
                    || mainHand.getItem() instanceof JumpWeaponItem
                    || offHand.getItem() instanceof AetherWaveSwordItem
                    || offHand.getItem() instanceof JumpWeaponItem;

            if (isHoldingValidWeapon) {
                return original * 2f; // Double the step height
            }
        }
        return original; // Default step height for other cases
    }
}
