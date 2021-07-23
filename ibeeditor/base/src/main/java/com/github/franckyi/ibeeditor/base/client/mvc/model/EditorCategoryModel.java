package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.AddListEntryEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EditorEntryModel;

import java.util.Collections;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class EditorCategoryModel implements Model {
    private final StringProperty nameProperty;
    private final BooleanProperty selectedProperty = DataBindings.getPropertyFactory().createBooleanProperty(false);
    private final BooleanProperty validProperty = DataBindings.getPropertyFactory().createBooleanProperty(true);
    private final ObservableList<EditorEntryModel> entries = DataBindings.getObservableListFactory().createObservableArrayList();
    private final ListEditorModel<?> editor;

    protected EditorCategoryModel(String name, ListEditorModel<?> editor) {
        nameProperty = DataBindings.getPropertyFactory().createStringProperty(name);
        this.editor = editor;
    }

    @Override
    public void initalize() {
        setupEntries();
        if (hasEntryList()) {
            getEntries().add(new AddListEntryEditorEntryModel(this, getAddListEntryButtonTooltip()));
        }
        updateValidity();
        validProperty().addListener(getEditor()::updateValidity);
        getEntries().addListener(this::updateValidity);
    }

    protected abstract void setupEntries();

    public String getName() {
        return nameProperty().getValue();
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public void setName(String value) {
        nameProperty().setValue(value);
    }

    public boolean isSelected() {
        return selectedProperty().getValue();
    }

    public BooleanProperty selectedProperty() {
        return selectedProperty;
    }

    public void setSelected(boolean value) {
        selectedProperty().setValue(value);
    }

    public boolean isValid() {
        return validProperty().getValue();
    }

    public BooleanProperty validProperty() {
        return validProperty;
    }

    public void setValid(boolean value) {
        validProperty().setValue(value);
    }

    public ListEditorModel<?> getEditor() {
        return editor;
    }

    public ObservableList<EditorEntryModel> getEntries() {
        return entries;
    }

    public void updateValidity() {
        setValid(getEntries().stream().allMatch(EditorEntryModel::isValid));
        if (hasEntryList()) {
            for (int i = 0; i < getEntryListSize(); i++) {
                EditorEntryModel entry = getEntries().get(getEntryListIndex(i));
                entry.setListSize(getEntryListSize());
                entry.setListIndex(i);
            }
        }
    }

    public int getEntryListStart() {
        return -1;
    }

    public int getEntryListIndex(int index) {
        return getEntryListStart() + index;
    }

    public int getEntryListSize() {
        return getEntries().size() - getEntryListStart() - 1;
    }

    public boolean hasEntryList() {
        return getEntryListStart() >= 0;
    }

    public void addEntryInList() {
        getEntries().add(getEntries().size() - 1, createListEntry());
    }

    public EditorEntryModel createListEntry() {
        return null;
    }

    public Text getAddListEntryButtonTooltip() {
        return translated("ibeeditor.gui.add").green();
    }

    public void moveEntryUp(int index) {
        Collections.swap(getEntries(), getEntryListIndex(index), getEntryListIndex(index) - 1);
    }

    public void moveEntryDown(int index) {
        Collections.swap(getEntries(), getEntryListIndex(index), getEntryListIndex(index) + 1);
    }

    public void deleteEntry(int index) {
        getEntries().remove(getEntryListIndex(index));
    }
}