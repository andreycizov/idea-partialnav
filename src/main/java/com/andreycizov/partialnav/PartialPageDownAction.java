package com.andreycizov.partialnav;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import org.jetbrains.annotations.NotNull;


public class PartialPageDownAction extends EditorAction {
    static String multPropertyName = "com.andreycizov.partialpagenav.mult.down";
    static float multPropertyDefault = (float) 1.0;
    static String staticPropertyName = "com.andreycizov.partialpagenav.stat.down";
    static int staticPropertyDefault = 80;
    static String navigationTypePropertyName = "com.andreycizov.partialpagenav.nav.type";
    static String MULT = "mult";
    static String STATIC = "static";

    public static class Handler extends EditorActionHandler {
        public Handler() {
            super(true);
        }

        @Override
        public void execute(@NotNull Editor editor, DataContext dataContext) {
            PropertiesComponent prop = PropertiesComponent.getInstance();
            PartialPageNavHelper.moveCaretPageDown(
                    editor,
                    false,
                    prop.getFloat(multPropertyName, multPropertyDefault),
                    prop.getInt(staticPropertyName, staticPropertyDefault),
                    prop.getValue(navigationTypePropertyName, MULT)
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