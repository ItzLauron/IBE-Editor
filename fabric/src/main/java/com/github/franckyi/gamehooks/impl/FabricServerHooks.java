package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ServerHooks;

public final class FabricServerHooks implements ServerHooks {
    public static final FabricServerHooks INSTANCE = new FabricServerHooks();

    private FabricServerHooks() {
    }
}
