package net.beez131.customlifestealweapons;

import net.beez131.customlifestealweapons.datagen.ModItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.enchantment.provider.EnchantmentProvider;

public class CustomlifestealweaponsDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		pack.addProvider(ModItemTagProvider::new);

		// Adding a provider example:
		//
		//pack.addProvider(AdvancementsProvider::new);
	}

}