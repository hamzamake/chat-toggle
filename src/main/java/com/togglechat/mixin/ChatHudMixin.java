package com.togglechat.mixin;

import com.togglechat.ToggleChatMod;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatComponent.class)
public class ChatHudMixin {

    @Inject(method = "extractRenderState", at = @At("HEAD"), cancellable = true)
    private void onExtractRenderState(CallbackInfo ci) {
        if (!ToggleChatMod.chatVisible) {
            ci.cancel();
        }
    }
}
