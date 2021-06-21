package com.github.franckyi.ibeeditor.impl.client.mvc.base;

import com.github.franckyi.ibeeditor.api.client.mvc.base.EditorEntryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.base.controller.EditorEntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.EditorEntryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry.EnumEditorEntryController;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry.IntegerEditorEntryController;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry.StringEditorEntryController;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry.TextEditorEntryController;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.EnumEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.IntegerEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.StringEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.TextEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.EnumEditorEntryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.IntegerEditorEntryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.StringEditorEntryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.TextEditorEntryView;

public class EditorEntryMVCImpl implements EditorEntryMVC {
    public static final EditorEntryMVC INSTANCE = new EditorEntryMVCImpl();

    @Override
    public EditorEntryView createViewAndBind(EditorEntryModel model) {
        EditorEntryController<?, ?> controller;
        switch (model.getType()) {
            case STRING:
                controller = new StringEditorEntryController((StringEditorEntryModel) model, new StringEditorEntryView());
                break;
            case INTEGER:
                controller = new IntegerEditorEntryController((IntegerEditorEntryModel) model, new IntegerEditorEntryView());
                break;
            case TEXT:
                controller = new TextEditorEntryController(((TextEditorEntryModel) model), new TextEditorEntryView());
                break;
            case ENUM:
                controller = createEnumController((EnumEditorEntryModel<?>) model);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + model.getType());
        }
        controller.bind();
        return controller.getView();
    }

    private <E extends Enum<E>> EditorEntryController<?, ?> createEnumController(EnumEditorEntryModel<E> model) {
        return new EnumEditorEntryController<>(model, new EnumEditorEntryView<>(model.getValue().getDeclaringClass()));
    }
}
