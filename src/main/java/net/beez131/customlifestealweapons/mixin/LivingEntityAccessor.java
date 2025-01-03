package net.beez131.customlifestealweapons.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
    @Accessor("stepHeight")
    float getStepHeight();

    @Accessor("stepHeight")
    void setStepHeight(float stepHeight);
}
