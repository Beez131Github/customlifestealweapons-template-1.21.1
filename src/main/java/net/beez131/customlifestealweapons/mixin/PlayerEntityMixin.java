package net.beez131.customlifestealweapons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.beez131.customlifestealweapons.item.custom.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable; // Used for modifying return values in methods.


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
                    || offHand.getItem() instanceof AetherWaveSwordItem
                    || mainHand.getItem() instanceof TyphonSwordItem
                    || offHand.getItem() instanceof TyphonSwordItem
                    || mainHand.getItem() instanceof RapidAegisSwordItem
                    || offHand.getItem() instanceof RapidAegisSwordItem;

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

            boolean isHoldingResistanceSword = mainHand.getItem() instanceof RapidAegisSwordItem
                    || offHand.getItem() instanceof RapidAegisSwordItem
                    || mainHand.getItem() instanceof ResistanceSwordItem
                    || offHand.getItem() instanceof ResistanceSwordItem
                    || mainHand.getItem() instanceof TyphonSwordItem
                    || offHand.getItem() instanceof TyphonSwordItem
                    || mainHand.getItem() instanceof PraesLeapSwordItem
                    || offHand.getItem() instanceof PraesLeapSwordItem;

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

            boolean isHoldingSlowFallingSword =
                    offHand.getItem() instanceof FlySwordItem
                    || offHand.getItem() instanceof AscendZephyrSwordItem || offHand.getItem() instanceof TyphonSwordItem;

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
                    || offHand.getItem() instanceof JumpWeaponItem
                    || mainHand.getItem() instanceof AscendZephyrSwordItem
                    || offHand.getItem() instanceof AscendZephyrSwordItem
                    || mainHand.getItem() instanceof TyphonSwordItem
                    || offHand.getItem() instanceof TyphonSwordItem
                    || mainHand.getItem() instanceof PraesLeapSwordItem
                    || offHand.getItem() instanceof PraesLeapSwordItem;

            if (isHoldingValidWeapon) {
                return original * 2f; // Double the step height
            }
        }
        return original; // Default step height for other cases
    }
    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    private void handleFallDamageMixin(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        // Ensure the entity is a player
        if ((Object) this instanceof PlayerEntity player) {
            boolean isHoldingFallDamageReductionItem = player.getInventory().containsAny(stack ->
                    stack.getItem() instanceof JumpWeaponItem || stack.getItem() instanceof AscendZephyrSwordItem || stack.getItem() instanceof AetherWaveSwordItem || stack.getItem() instanceof PraesLeapSwordItem || stack.getItem() instanceof TyphonSwordItem
            );

            if (isHoldingFallDamageReductionItem) {
                // Reduce fall damage by 20 blocks
                float adjustedFallDistance = fallDistance - 20;

                // If adjusted fall distance is <= 0 blocks, no damage
                if (adjustedFallDistance <= 0) {
                    cir.setReturnValue(false); // Cancels fall damage handling
                    return;
                }

                // Recalculate fall damage
                player.damage(player.getDamageSources().fall(), adjustedFallDistance * damageMultiplier);
                cir.setReturnValue(true); // Mark fall damage as handled
            }
        }
    }





}

