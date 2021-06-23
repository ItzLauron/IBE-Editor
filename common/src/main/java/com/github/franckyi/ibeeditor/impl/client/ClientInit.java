package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientInit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init(MinecraftClient client) {
        LOGGER.debug("Initializing IBE Editor - client");
        Minecraft.setClient(client);
        ClientConfiguration.load();
        GUAPI.init();
        syncGuapiConfig();
        KeyBindings.init();
    }

    public static void syncGuapiConfig() {
        GUAPI.setDebugMode(ClientConfiguration.INSTANCE.getGuapiDebugMode());
        GUAPI.setTheme(ClientConfiguration.INSTANCE.getGuapiTheme());
    }
}
