package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

import java.util.function.Consumer;

public class BooleanEntryModel extends ValueEntryModel<Boolean> {
    public BooleanEntryModel(CategoryModel category, IText label, boolean value, Consumer<Boolean> action) {
        super(category, label, value, action);
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }
}
