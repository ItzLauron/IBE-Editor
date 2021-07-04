package com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.view;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.ListEditorView;
import com.github.franckyi.minecraft.api.common.text.builder.TranslatedTextBuilder;

import java.util.function.Consumer;

public interface StandardEditorView extends ListEditorView {
    TranslatedTextBuilder getHeaderText();

    default boolean isShowTextButtons() {
        return showTextButtonsProperty().getValue();
    }

    BooleanProperty showTextButtonsProperty();

    default void setShowTextButtons(boolean value) {
        showTextButtonsProperty().setValue(value);
    }

    void setOnTextButtonClick(Consumer<TextButtonType> action);

    enum TextButtonType {
        BOLD(false), ITALIC(false), UNDERLINE(false), STRIKETHROUGH(false), OBFUSCATED(false), BLACK, DARK_BLUE,
        DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE,
        YELLOW, WHITE, CUSTOM_COLOR(false);
        private final boolean color;

        TextButtonType(boolean color) {
            this.color = color;
        }

        TextButtonType() {
            this(true);
        }

        public boolean isColor() {
            return color;
        }
    }
}
