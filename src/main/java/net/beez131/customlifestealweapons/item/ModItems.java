package net.beez131.customlifestealweapons.item;

import net.beez131.customlifestealweapons.Customlifestealweapons;
import net.beez131.customlifestealweapons.item.custom.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
//import net.beez131.customlifestealweapons.item.custom.KnifeItem;

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

    public static final Item POISON_AXE = registerItem("poison_axe",
            new PoisonWeaponItem(ModToolMaterials.POISON, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.POISON, 5, -3.0f))));

    public static final Item WEAKNESS_AXE = registerItem("weakness_axe",
            new WeaknessAxeItem(ModToolMaterials.WEAKNESS, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.WEAKNESS, 5, -3.0f))));

    public static final Item HUNGER_AXE = registerItem("hunger_axe",
            new WeaknessAxeItem(ModToolMaterials.HUNGER, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.HUNGER, 5, -3.0f))));

    public static final Item VISION_AXE = registerItem("vision_axe",
            new VisionAxeItem(ModToolMaterials.VISION, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.VISION, 5, -3.0f))));

    public static final Item PHOBOS_AXE = registerItem("phobos_axe",
            new PhobosAxeItem(ModToolMaterials.PHOBOS, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.PHOBOS, 7, -3.0f))));

    public static final Item SPEED_SWORD = registerItem("speed_sword",
            new SpeedSwordItem(ModToolMaterials.SPEED, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.SPEED, 3, -2.3f))));

    public static final Item JUMP_SWORD = registerItem("jump_sword",
            new JumpWeaponItem(ModToolMaterials.JUMP, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.JUMP, 4, -2.5f))));

    public static final Item ASCENDZEPHYR_SWORD = registerItem("ascendzephyr_sword",
            new AscendZephyrSwordItem(ModToolMaterials.ASCEND, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ASCEND, 4, -2.4f))));

    public static final Item RAPIDAEGIS_SWORD = registerItem("rapidaegis_sword",
            new RapidAegisSwordItem(ModToolMaterials.RAPID, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.RAPID, 4, -2.4f))));

    public static final Item AETHERWAVE_SWORD = registerItem("aetherwave_sword",
            new AetherWaveSwordItem(ModToolMaterials.AETHER, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.AETHER, 4, -2.4f))));

    public static final Item PRAESLEAP_SWORD = registerItem("praesleap_sword",
            new PraesLeapSwordItem(ModToolMaterials.PRAES, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.PRAES, 5, -2.6f))));

    public static final Item FLIGHT_SWORD = registerItem("flight_sword",
            new FlySwordItem(ModToolMaterials.FLIGHT, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.FLIGHT, 3, -2.3f))));

    public static final Item RESISTANCE_SWORD = registerItem("resistance_sword",
            new ResistanceSwordItem(ModToolMaterials.RESISTANCE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.RESISTANCE, 4, -2.5f))));

    public static final Item TYPHON_SWORD = registerItem("typhon_sword",
            new TyphonSwordItem(ModToolMaterials.TYPHON, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.TYPHON, 6, -2.2f))));


    // Shards
    public static final Item POISON_SHARD = registerItem("poison_shard", new Item(new Item.Settings()));
    public static final Item GLOW_SHARD = registerItem("glow_shard", new Item(new Item.Settings()));
    public static final Item WEAKNESS_SHARD = registerItem("weakness_shard", new Item(new Item.Settings()));



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
            entries.add(POISON_AXE);
            entries.add(WEAKNESS_AXE);
            entries.add(VISION_AXE);
            entries.add(HUNGER_AXE);
            entries.add(PHOBOS_AXE);
            entries.add(SPEED_SWORD);
            entries.add(JUMP_SWORD);
            entries.add(FLIGHT_SWORD);
            entries.add(RESISTANCE_SWORD);
            entries.add(ASCENDZEPHYR_SWORD);
            entries.add(RAPIDAEGIS_SWORD);
            entries.add(AETHERWAVE_SWORD);
            entries.add(PRAESLEAP_SWORD);
            entries.add(TYPHON_SWORD);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(POISON_SHARD);
            entries.add(WEAKNESS_SHARD);
            entries.add(GLOW_SHARD);

        });
    }
}
