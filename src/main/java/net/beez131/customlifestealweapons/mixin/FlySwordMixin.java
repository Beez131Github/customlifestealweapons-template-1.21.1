package net.beez131.customlifestealweapons.mixin;

import net.beez131.customlifestealweapons.item.custom.FlySwordItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class FlySwordMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void grantFlight(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack stack = (ItemStack) (Object) this;

        // Check if the item is a Fly Sword
        if (stack.getItem() instanceof FlySwordItem) {
            // Make sure we are on the server side
            if (!world.isClient()) {
                // If the cooldown is not active, grant flight
                if (!user.getItemCooldownManager().isCoolingDown(stack.getItem())) {
                    // Mark the item as being on cooldown
                    user.getItemCooldownManager().set(stack.getItem(), 200); // 200 ticks cooldown

                    // Activate flight for the player
                    user.getAbilities().allowFlying = true;
                    user.getAbilities().flying = true;
                    user.sendAbilitiesUpdate();

                    // Schedule disabling flight after 5 seconds (100 ticks)
                    world.getServer().execute(() -> {
                        user.getAbilities().allowFlying = false;
                        user.getAbilities().flying = false;
                        user.sendAbilitiesUpdate();
                    });

                    // Return success, indicating the item used was handled
                    cir.setReturnValue(TypedActionResult.success(stack));
                }
            }
        }
    }

}
