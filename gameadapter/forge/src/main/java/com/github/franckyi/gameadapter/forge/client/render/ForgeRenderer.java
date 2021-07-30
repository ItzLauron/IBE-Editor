package com.github.franckyi.gameadapter.forge.client.render;

import com.github.franckyi.gameadapter.api.client.render.Matrices;
import com.github.franckyi.gameadapter.api.client.render.Renderer;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.List;
import java.util.stream.Collectors;

public class ForgeRenderer implements Renderer {
    public static final Renderer INSTANCE = new ForgeRenderer();

    private ForgeRenderer() {
    }

    private net.minecraft.client.gui.FontRenderer font() {
        return Minecraft.getInstance().font;
    }

    @Override
    public int getFontHeight(Text text) {
        return font().lineHeight;
    }

    @Override
    public int getFontWidth(Text text) {
        return font().width((ITextComponent) text.get());
    }

    @Override
    public void drawString(Matrices matrices, Text text, float x, float y, int color, boolean shadow) {
        if (shadow) {
            font().drawShadow(matrices.get(), (ITextComponent) text.get(), x, y, color);
        } else {
            font().draw(matrices.get(), (ITextComponent) text.get(), x, y, color);
        }
    }

    @Override
    public void fillRectangle(Matrices matrices, int x0, int y0, int x1, int y1, int color) {
        AbstractGui.fill(matrices.get(), x0, y0, x1, y1, color);
    }

    @Override
    public void drawTexture(Matrices matrices, String id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight) {
        Minecraft.getInstance().getTextureManager().bind(new ResourceLocation(id));
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        AbstractGui.blit(matrices.get(), x, y, 0, imageX, imageY, width, height, imageHeight, imageWidth);
    }

    @Override
    public void drawSprite(Matrices matrices, Object spriteObj, int x, int y, int imageWidth, int imageHeight) {
        TextureAtlasSprite sprite = (TextureAtlasSprite) spriteObj;
        Minecraft.getInstance().getTextureManager().bind(sprite.atlas().location());
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        AbstractGui.blit(matrices.get(), x, y, 0, imageWidth, imageHeight, sprite);
    }

    @Override
    public void drawTooltip(Matrices matrices, List<Text> text, int x, int y) {
        Minecraft.getInstance().screen.renderComponentTooltip(matrices.get(), text.stream().<ITextComponent>map(Text::get).collect(Collectors.toList()), x, y);
    }

    @Override
    public void drawTooltip(Matrices matrices, Item item, int x, int y) {
        Minecraft.getInstance().screen.renderComponentTooltip(matrices.get(),
                ((ItemStack) item.get()).getTooltipLines(Minecraft.getInstance().player, ITooltipFlag.TooltipFlags.NORMAL), x, y);
    }

    @Override
    public void drawItem(Item item, int x, int y) {
        Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(item.get(), x, y);
    }
}
