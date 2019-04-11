package com.andreycizov.partialnav;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import org.jetbrains.annotations.NotNull;


public class PartialPageUpWithSelectionAction extends EditorAction {
    static String propertyName = "com.andreycizov.partialpagenav.mult.up";
    static float propertyDefault = (float) 1.0;

    public static class Handler extends EditorActionHandler {
        public Handler() {
            super(true);
        }

        @Override
        public void execute(@NotNull Editor editor, DataContext dataContext) {
            PartialPageNavHelper.moveCaretPageUp(
                    editor,
                    true,
                    PropertiesComponent.getInstance().getFloat(propertyName, propertyDefault)
            );
        }

        @Override
        public boolean isEnabled(Editor editor, DataContext dataContext) {
            return !editor.isOneLineMode();
        }
    }

    public PartialPageUpWithSelectionAction() {
        super(new Handler());
    }
}
