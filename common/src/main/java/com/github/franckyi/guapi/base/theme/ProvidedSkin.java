package com.github.franckyi.guapi.base.theme;

import com.github.franckyi.guapi.base.node.AbstractNode;
import com.mojang.blaze3d.vertex.PoseStack;

public abstract class ProvidedSkin<N extends AbstractNode> extends AbstractSkin<N> {
    private final N node;

    protected ProvidedSkin(N node) {
        this.node = node;
    }

    @Override
    public void render(N node, PoseStack matrices, int mouseX, int mouseY, float delta) {
        super.render(node, matrices, mouseX, mouseY, delta);
        renderNode(matrices, mouseX, mouseY, delta);
    }

    protected abstract <M> void renderNode(M matrices, int mouseX, int mouseY, float delta);

    public N getNode() {
        return node;
    }
}