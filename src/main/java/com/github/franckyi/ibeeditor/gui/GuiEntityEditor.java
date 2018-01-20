package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GuiEntityEditor extends GuiEditor {


    protected GuiEntityEditor(Map<String, List<BaseProperty<?>>> categories, GuiScreen parentScreen) {
        super(categories, parentScreen);
    }

    protected GuiEntityEditor(Map<String, List<BaseProperty<?>>> categories) {
        super(categories);
    }

    @Override
    protected void apply(Set<BaseProperty<?>> properties) {

    }
}
