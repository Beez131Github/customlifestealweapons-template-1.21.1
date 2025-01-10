package net.beez131.customlifestealweapons.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.beez131.customlifestealweapons.item.custom.PhobosAxeItem;
import net.beez131.customlifestealweapons.item.custom.TyphonSwordItem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

public class ClientTickHandler {
    private static int redOverlayTicks = 0;

    public static void registerHandlers() {
        // Client tick logic
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                boolean hasTyphonSword = false;
                boolean hasPhobosAxe = false;

                // Check if the player has both items
                for (ItemStack stack : client.player.getInventory().main) {
                    if (stack.getItem() instanceof TyphonSwordItem) {
                        hasTyphonSword = true;
                    } else if (stack.getItem() instanceof PhobosAxeItem) {
                        hasPhobosAxe = true;
                    }
                    if (hasTyphonSword && hasPhobosAxe) break;
                }

                // Start red overlay if both items are held
                if (hasTyphonSword && hasPhobosAxe) {
                    redOverlayTicks = 60; // 3 seconds (20 ticks per second)
                }

                // Count down red overlay ticks
                if (redOverlayTicks > 0) {
                    redOverlayTicks--;
                }
            }
        });

        // HUD rendering logic
        HudRenderCallback.EVENT.register((drawContext, delta) -> {
            if (redOverlayTicks > 0) {
                drawRedGradient(drawContext);
            }
        });
    }

    private static void drawRedGradient(DrawContext context) {
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0f, 0.0f, 0.0f, 0.5f); // Semi-transparent red

        MinecraftClient client = MinecraftClient.getInstance();
        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();

        // Fill the screen with a red gradient
        context.fill(0, 0, screenWidth, screenHeight, 0x80FF0000); // 0x80FF0000 = 50% transparent red

        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
    }
}
