package net.beez131.customlifestealweapons.item;

import net.beez131.customlifestealweapons.Customlifestealweapons;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item AMETHYST_KNIFE = registerItem("amethyst_knife",
            new AxeItem(ModToolMaterials.AMETHYST, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.AMETHYST, 2, -2.1f))));

    public static final Item COPPER_KNIFE = registerItem("copper_knife",
            new AxeItem(ModToolMaterials.COPPER, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.COPPER, 2, -2.1f))));

    public static final Item QUARTZ_KNIFE = registerItem("quartz_knife",
            new AxeItem(ModToolMaterials.QUARTZ, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.QUARTZ, 2, -2.1f))));

    public static final Item ECHO_KNIFE = registerItem("echo_knife",
            new AxeItem(ModToolMaterials.ECHO, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.ECHO, 2, -2.1f))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Customlifestealweapons.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Customlifestealweapons.LOGGER.info("Registering Mod Items for " + Customlifestealweapons.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(AMETHYST_KNIFE);
            entries.add(ECHO_KNIFE);
            entries.add(QUARTZ_KNIFE);
            entries.add(COPPER_KNIFE);
        });
    }
}
