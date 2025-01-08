package net.beez131.customlifestealweapons.item;

import com.google.common.base.Suppliers;
import net.beez131.customlifestealweapons.block.ModBlocks;
import net.beez131.customlifestealweapons.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    AMETHYST(ModTags.Blocks.INCORRECT_FOR_AMETHYST_TOOL, 250, 1.0F, 2.0F, 20, () -> Ingredient.ofItems(Items.AMETHYST_SHARD)),
    COPPER(ModTags.Blocks.INCORRECT_FOR_COPPER_TOOL, 131, 1.0F, 1.0F, 15, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    QUARTZ(ModTags.Blocks.INCORRECT_FOR_QUARTZ_TOOL, 1561, 1.0F, 3.0F, 25, () -> Ingredient.ofItems(Items.QUARTZ)),
    ECHO(ModTags.Blocks.INCORRECT_FOR_ECHO_TOOL, 2031, 1.0F, 4.0F, 30, () -> Ingredient.ofItems(Items.ECHO_SHARD)),
    WEAKNESS(ModTags.Blocks.INCORRECT_FOR_WEAKNESS_TOOL, 4062, 10.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    HUNGER(ModTags.Blocks.INCORRECT_FOR_HUNGER_TOOL, 4062, 10.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    VISION(ModTags.Blocks.INCORRECT_FOR_VISION_TOOL, 4062, 5.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    SPEED(ModTags.Blocks.INCORRECT_FOR_SPEED_TOOL, 4062, 5.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    JUMP(ModTags.Blocks.INCORRECT_FOR_JUMP_TOOL, 4062, 5.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    AETHER(ModTags.Blocks.INCORRECT_FOR_AETHERWAVE_TOOL, 4062, 6.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    ASCEND(ModTags.Blocks.INCORRECT_FOR_ASCEND_TOOL, 4062, 6.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    RAPID(ModTags.Blocks.INCORRECT_FOR_RAPID_TOOL, 4062, 6.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    PRAES(ModTags.Blocks.INCORRECT_FOR_PRAES_TOOL, 4062, 6.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    TYPHON(ModTags.Blocks.INCORRECT_FOR_TYPHON_TOOL, 8124, 7.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    PHOBOS(ModTags.Blocks.INCORRECT_FOR_PHOBOS_TOOL, 8124, 7.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    FLIGHT(ModTags.Blocks.INCORRECT_FOR_FLIGHT_TOOL, 4062, 5.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    RESISTANCE(ModTags.Blocks.INCORRECT_FOR_RESISTANCE_TOOL, 4062, 5.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE)),
    POISON(ModTags.Blocks.INCORRECT_FOR_POISON_TOOL, 4062, 1.0F, 4.0F, 30, () -> Ingredient.ofItems(ModBlocks.OBSIDITE));


    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    private ModToolMaterials(
            final TagKey<Block> inverseTag,
            final int itemDurability,
            final float miningSpeed,
            final float attackDamage,
            final int enchantability,
            final Supplier<Ingredient> repairIngredient
    ) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
