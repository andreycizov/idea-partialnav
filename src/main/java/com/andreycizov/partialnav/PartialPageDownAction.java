package com.andreycizov.partialnav;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import org.jetbrains.annotations.NotNull;


public class PartialPageDownAction extends EditorAction {
    static String propertyName = "com.andreycizov.partialpagenav.mult.down";
    static float propertyDefault = (float) 1.0;

    public static class Handler extends EditorActionHandler {
        public Handler() {
            super(true);
        }

        @Override
        public void execute(@NotNull Editor editor, DataContext dataContext) {
            PartialPageNavHelper.moveCaretPageDown(
                    editor,
                    false,
                    PropertiesComponent.getInstance().getFloat(propertyName, propertyDefault)
            );
        }

        @Override
        public boolean isEnabled(Editor editor, DataContext dataContext) {
            return !editor.isOneLineMode();
        }
    }

    public PartialPageDownAction() {
        super(new Handler());
    }
}