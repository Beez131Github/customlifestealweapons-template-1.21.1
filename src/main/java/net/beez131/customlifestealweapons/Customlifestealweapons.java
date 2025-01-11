package net.beez131.customlifestealweapons;

import net.beez131.customlifestealweapons.block.ModBlocks;
import net.beez131.customlifestealweapons.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.player.PlayerEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customlifestealweapons implements ModInitializer {
	public static final String MOD_ID = "customlifestealweapons";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

	}
}
