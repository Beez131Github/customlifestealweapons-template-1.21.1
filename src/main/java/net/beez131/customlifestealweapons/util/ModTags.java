package net.beez131.customlifestealweapons.util;

import net.beez131.customlifestealweapons.Customlifestealweapons;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_AMETHYST_TOOL = createTag("needs_amethyst_tool");
        public static final TagKey<Block> INCORRECT_FOR_AMETHYST_TOOL = createTag("incorrect_for_amethyst_tool");

        public static final TagKey<Block> NEEDS_COPPER_TOOL = createTag("needs_copper_tool");
        public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = createTag("incorrect_for_copper_tool");

        public static final TagKey<Block> NEEDS_QUARTZ_TOOL = createTag("needs_quartz_tool");
        public static final TagKey<Block> INCORRECT_FOR_QUARTZ_TOOL = createTag("incorrect_for_quartz_tool");

        public static final TagKey<Block> NEEDS_ECHO_TOOL = createTag("needs_echo_tool");
        public static final TagKey<Block> INCORRECT_FOR_ECHO_TOOL = createTag("incorrect_for_echo_tool");

        public static final TagKey<Block> NEEDS_POISON_TOOL = createTag("needs_poison_tool");
        public static final TagKey<Block> INCORRECT_FOR_POISON_TOOL = createTag("incorrect_for_poison_tool");

        public static final TagKey<Block> NEEDS_VISION_TOOL = createTag("needs_vision_tool");
        public static final TagKey<Block> INCORRECT_FOR_VISION_TOOL = createTag("incorrect_for_vision_tool");

        public static final TagKey<Block> NEEDS_WEAKNESS_TOOL = createTag("needs_weakness_tool");
        public static final TagKey<Block> INCORRECT_FOR_WEAKNESS_TOOL = createTag("incorrect_for_weakness_tool");

        public static final TagKey<Block> NEEDS_SPEED_TOOL = createTag("needs_speed_tool");
        public static final TagKey<Block> INCORRECT_FOR_SPEED_TOOL = createTag("incorrect_for_speed_tool");

        public static final TagKey<Block> NEEDS_JUMP_TOOL = createTag("needs_jump_tool");
        public static final TagKey<Block> INCORRECT_FOR_JUMP_TOOL = createTag("incorrect_for_jump_tool");

        public static final TagKey<Block> NEEDS_AETHERWAVE_TOOL = createTag("needs_aetherwave_tool");
        public static final TagKey<Block> INCORRECT_FOR_AETHERWAVE_TOOL = createTag("incorrect_for_aetherwave_tool");

        public static final TagKey<Block> NEEDS_FLIGHT_TOOL = createTag("needs_flight_tool");
        public static final TagKey<Block> INCORRECT_FOR_FLIGHT_TOOL = createTag("incorrect_for_flight_tool");

        public static final TagKey<Block> NEEDS_RESISTANCE_TOOL = createTag("needs_resistance_tool");
        public static final TagKey<Block> INCORRECT_FOR_RESISTANCE_TOOL = createTag("incorrect_for_resistance_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Customlifestealweapons.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> POISON_WEAPONS = createTag("poison_item");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Customlifestealweapons.MOD_ID, name));
        }
    }
}
