package com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry;

import com.github.franckyi.guapi.api.node.TextField;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public abstract class TextFieldEntryView extends LabeledEntryView {
    protected final TextField textField;

    protected TextFieldEntryView() {
        super();
        root.getChildren().add(textField = textField());
        root.setWeight(textField, 2);
    }

    public TextField getTextField() {
        return textField;
    }
}
