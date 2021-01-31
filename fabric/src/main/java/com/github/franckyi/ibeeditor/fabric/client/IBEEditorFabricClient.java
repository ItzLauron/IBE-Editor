package com.github.franckyi.ibeeditor.fabric.client;

import com.github.franckyi.guapi.common.GUAPI;
import com.github.franckyi.guapi.common.Scene;
import com.github.franckyi.guapi.common.node.Label;
import com.github.franckyi.guapi.fabric.FabricRenderer;
import com.github.franckyi.guapi.fabric.FabricScreenHandler;
import com.github.franckyi.ibeeditor.common.IBEEditorCommonTest;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class IBEEditorFabricClient implements ClientModInitializer {
    public static KeyBinding keyBinding;
    @Override
    public void onInitializeClient() {
        GUAPI.init(new FabricRenderer(), new FabricScreenHandler());
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "ibeeditor.key.editor",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_I,
                "ibeeditor.category"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                IBEEditorCommonTest.openEditor("Hello World from Fabric!");
            }
        });
    }
}