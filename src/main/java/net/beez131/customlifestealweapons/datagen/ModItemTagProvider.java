package net.beez131.customlifestealweapons.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.beez131.customlifestealweapons.item.ModItems;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(ModItems.ECHO_KNIFE)
                .add(ModItems.COPPER_KNIFE)
                .add(ModItems.QUARTZ_KNIFE)
                .add(ModItems.POISON_KNIFE)
                .add(ModItems.VISION_SWORD)
                .add(ModItems.WEAKNESS_AXE)
                .add(ModItems.JUMP_SWORD)
                .add(ModItems.SPEED_SWORD)
                .add(ModItems.AETHERWAVE_SWORD)
                .add(ModItems.AMETHYST_KNIFE);
        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.ECHO_KNIFE)
                .add(ModItems.COPPER_KNIFE)
                .add(ModItems.QUARTZ_KNIFE)
                .add(ModItems.POISON_KNIFE)
                .add(ModItems.VISION_SWORD)
                .add(ModItems.WEAKNESS_AXE)
                .add(ModItems.JUMP_SWORD)
                .add(ModItems.SPEED_SWORD)
                .add(ModItems.AETHERWAVE_SWORD)
                .add(ModItems.AMETHYST_KNIFE);
        getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE)
                .add(ModItems.JUMP_SWORD)
                .add(ModItems.SPEED_SWORD)
                .add(ModItems.AETHERWAVE_SWORD)
                .add(ModItems.VISION_SWORD);
        getOrCreateTagBuilder(ItemTags.MINING_ENCHANTABLE)
                .add(ModItems.WEAKNESS_AXE);


    }
}


