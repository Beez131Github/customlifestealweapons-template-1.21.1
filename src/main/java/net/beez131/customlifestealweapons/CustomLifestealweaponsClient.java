package net.beez131.customlifestealweapons;

import net.beez131.customlifestealweapons.client.ClientTickHandler;
import net.fabricmc.api.ClientModInitializer;

public class CustomLifestealweaponsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickHandler.registerHandlers();
    }
}
