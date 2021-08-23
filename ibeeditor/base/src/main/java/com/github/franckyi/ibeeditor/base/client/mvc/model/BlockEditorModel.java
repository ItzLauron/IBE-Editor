package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.world.BlockData;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.BlockCategoryModel;

import java.util.function.Consumer;

public class BlockEditorModel extends StandardEditorModel<BlockData, BlockCategoryModel> {
    public BlockEditorModel(BlockData block, Consumer<BlockData> action, IText disabledTooltip) {
        super(block, action, disabledTooltip, "ibeeditor.text.block");
    }

    @Override
    protected void setupCategories() {
    }

    @Override
    public BlockData applyChanges() {
        return getTarget(); // TODO
    }
}
