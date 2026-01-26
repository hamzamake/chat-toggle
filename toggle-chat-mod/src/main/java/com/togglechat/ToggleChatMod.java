package com.togglechat;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class ToggleChatMod implements ClientModInitializer {
    
    public static boolean chatVisible = true;
    
    private static KeyBinding toggleChatKey;
    
    @Override
    public void onInitializeClient() {
        // Register the keybind (default: H key)
        // In 1.21.11 Yarn mappings, KeyBinding constructor takes:
        // (String id, int code, KeyBinding.Category category)
        toggleChatKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.togglechat.toggle",
            GLFW.GLFW_KEY_H,
            KeyBinding.Category.MISC
        ));
        
        // Listen for key presses
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleChatKey.wasPressed()) {
                chatVisible = !chatVisible;
                if (client.player != null) {
                    if (chatVisible) {
                        client.player.sendMessage(
                            Text.literal("§aChat GUI enabled"),
                            true
                        );
                    } else {
                        client.player.sendMessage(
                            Text.literal("§cChat GUI disabled"),
                            true
                        );
                    }
                }
            }
        });
    }
}
