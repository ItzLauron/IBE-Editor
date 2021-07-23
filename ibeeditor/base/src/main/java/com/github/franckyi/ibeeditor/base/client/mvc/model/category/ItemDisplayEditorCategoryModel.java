package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.TextHandler;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.ListTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import com.github.franckyi.gameadapter.api.common.text.PlainText;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.util.Color;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.TextEditorEntryModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemDisplayEditorCategoryModel extends ItemEditorCategoryModel {
    private ListTag newLore;

    public ItemDisplayEditorCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.editor.category.display", editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new TextEditorEntryModel(this, translated("ibeeditor.gui.editor.item.entry.custom_name"), getItemName(), this::setItemName));
        ListTag loreList = getBaseDisplay().getList("Lore", Tag.STRING_ID);
        for (int i = 0; i < loreList.size(); i++) {
            getEntries().add(createLoreEntry((PlainText) TextHandler.getSerializer().fromJson(loreList.getString(i), Text.class)));
        }
    }

    @Override
    public int getEntryListStart() {
        return 1;
    }

    @Override
    public Text getAddListEntryButtonTooltip() {
        return translated("ibeeditor.gui.editor.item.entry.lore_add").green();
    }

    @Override
    public EditorEntryModel createListEntry() {
        return createLoreEntry(null);
    }

    private EditorEntryModel createLoreEntry(PlainText value) {
        TextEditorEntryModel entry = new TextEditorEntryModel(this, null, value, this::addLore);
        entry.listIndexProperty().addListener(index -> entry.setLabel(translated("ibeeditor.gui.editor.item.entry.lore").with(text(Integer.toString(index)))));
        return entry;
    }

    @Override
    public void apply(CompoundTag nbt) {
        newLore = Game.getCommon().getTagFactory().createListTag();
        super.apply(nbt);
        getNewDisplay().putTag("Lore", newLore);
        if (getNewDisplay().getList("Lore", Tag.STRING_ID).isEmpty()) {
            getNewDisplay().remove("Lore");
        }
        if (getNewDisplay().isEmpty()) {
            getNewTag().remove("display");
        }
    }

    private PlainText getItemName() {
        String s = getBaseDisplay().getString("Name");
        return s.isEmpty() ? null : (PlainText) TextHandler.getSerializer().fromJson(s, Text.class);
    }

    private void setItemName(PlainText value) {
        if (!value.getRawText().isEmpty()) {
            if (value.getExtra() != null && !value.getExtra().isEmpty()) {
                Text firstText = value.getExtra().get(0);
                if (firstText.isItalic()) {
                    value.getExtra().add(0, text("").italic(false));
                } else {
                    firstText.setItalic(false);
                }
            }
            getNewDisplay().putString("Name", TextHandler.getSerializer().toJson(value));
        } else if (getNewTag().contains("display", Tag.COMPOUND_ID)) {
            getNewDisplay().remove("Name");
        }
    }

    private void addLore(PlainText value) {
        if (!value.getRawText().isEmpty()) {
            if (value.getExtra() != null && !value.getExtra().isEmpty()) {
                Text firstText = value.getExtra().get(0);
                if (firstText.isItalic() || Color.DARK_PURPLE.equals(firstText.getColor())) {
                    value.getExtra().add(0, text("").italic(false).color(Color.WHITE));
                } else {
                    firstText.setItalic(false);
                    firstText.setColor(Color.WHITE);
                }
            }
        }
        newLore.addString(TextHandler.getSerializer().toJson(value));
    }

    private CompoundTag getBaseDisplay() {
        return getBaseTag().getCompound("display");
    }

    private CompoundTag getNewDisplay() {
        return getNewTag().getOrCreateCompound("display");
    }
}