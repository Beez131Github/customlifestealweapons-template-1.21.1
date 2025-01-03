package net.beez131.customlifestealweapons.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.beez131.customlifestealweapons.item.custom.AetherWaveSwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void modifyStepHeight(CallbackInfo info) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        // Use the LivingEntityAccessor to modify stepHeight
        if (player.getMainHandStack().getItem() instanceof AetherWaveSwordItem ||
                player.getOffHandStack().getItem() instanceof AetherWaveSwordItem) {
            ((LivingEntityAccessor) player).setStepHeight(1.0F); // Allow stepping up 1-block tall obstacles
        } else {
            ((LivingEntityAccessor) player).setStepHeight(0.6F); // Reset to default
        }
    }
}
