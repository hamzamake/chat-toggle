package com.togglechat;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class ToggleChatMod implements ClientModInitializer {
    
    public static boolean chatVisible = true;
    
    private static KeyMapping toggleChatKey;
    
    @Override
    public void onInitializeClient() {
        toggleChatKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            "key.togglechat.toggle",
            GLFW.GLFW_KEY_H,
            KeyMapping.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleChatKey.consumeClick()) {
                chatVisible = !chatVisible;
                if (client.player != null) {
                    if (chatVisible) {
                        client.player.sendSystemMessage(
                            Component.literal("Chat GUI enabled").withStyle(ChatFormatting.GREEN)
                        );
                    } else {
                        client.player.sendSystemMessage(
                            Component.literal("Chat GUI disabled").withStyle(ChatFormatting.RED)
                        );
                    }
                }
            }
        });
    }
}
