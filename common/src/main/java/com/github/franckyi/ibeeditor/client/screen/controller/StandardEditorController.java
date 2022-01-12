package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.guapi.api.node.Group;
import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.screen.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.StandardEditorView;
import com.github.franckyi.ibeeditor.common.ModTexts;

public class StandardEditorController extends CategoryEntryScreenController<StandardEditorModel<?, ?>, StandardEditorView> {
    public StandardEditorController(StandardEditorModel<?, ?> model, StandardEditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        if (!model.canSaveToVault()) {
            ((Group) view.getSaveVaultButton().getParent()).getChildren().remove(view.getSaveVaultButton());
        }
        model.saveToVaultProperty().bind(view.getSaveVaultButton().activeProperty());
        view.getHeaderLabel().setLabel(ModTexts.editorTitle(model.getTitle()));
        model.activeTextEditorProperty().addListener(value -> view.setShowTextButtons(value != null));
        view.setTextEditorSupplier(model::getActiveTextEditor);
        if (model.isDisabled()) {
            view.getDoneButton().setDisable(true);
            view.getDoneButton().getTooltip().add(model.getDisabledTooltip());
        }
        view.getChooseCustomColorButton().onAction(e -> {
            e.consume();
            ModScreenHandler.openColorSelectionScreen(ColorSelectionScreenModel.Target.TEXT, Color.fromHex(model.getCurrentCustomColor()), this::updateCustomColor);
        });
        model.currentCustomColorProperty().addListener(value -> {
            view.getCustomColorButton().setBackgroundColor(Color.fromHex(value));
            view.getCustomColorButton().setVisible(value != null);
        });
        view.getCustomColorButton().onAction(e -> {
            e.consume();
            model.getActiveTextEditor().addColorFormatting(model.getCurrentCustomColor());
        });
    }

    private void updateCustomColor(String hex) {
        model.setCurrentCustomColor(hex);
        model.getActiveTextEditor().addColorFormatting(hex);
    }

    @Override
    protected void onValidationChange(boolean newVal) {
        if (!model.isDisabled()) {
            super.onValidationChange(newVal);
        }
    }
}