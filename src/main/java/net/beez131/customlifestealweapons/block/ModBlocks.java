package net.beez131.customlifestealweapons.block;

import net.beez131.customlifestealweapons.Customlifestealweapons;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block OBSIDITE = registerBlock("obsidite",
            new Block(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));

    public static final Block POLISHED_OBSIDITE = registerBlock("polished_obsidite",
            new Block(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Customlifestealweapons.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Customlifestealweapons.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Customlifestealweapons.LOGGER.info("Registering blocks for " + Customlifestealweapons.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.OBSIDITE);
            entries.add(ModBlocks.POLISHED_OBSIDITE);
        });
    }
}
