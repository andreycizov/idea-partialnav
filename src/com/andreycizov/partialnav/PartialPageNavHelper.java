package com.andreycizov.partialnav;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.util.SystemProperties;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class PartialPageNavHelper {
    //  https://github.com/JetBrains/intellij-community/blob/5107d68347f99578759f406fb128bb0907b820ea/platform/platform-impl/src/com/intellij/openapi/editor/actions/EditorActionUtil.java#L791
    static Rectangle getVisibleArea(@NotNull Editor editor) {
        return SystemProperties.isTrueSmoothScrollingEnabled() ? editor.getScrollingModel().getVisibleAreaOnScrollingFinished()
                : editor.getScrollingModel().getVisibleArea();
    }

    // https://github.com/JetBrains/intellij-community/blob/5107d68347f99578759f406fb128bb0907b820ea/platform/platform-impl/src/com/intellij/openapi/editor/actions/EditorActionUtil.java#L743
    public static void moveCaretPageUp(@NotNull Editor editor, boolean isWithSelection, float mult) {
        int lineHeight = editor.getLineHeight();
        Rectangle visibleArea = getVisibleArea(editor);
        int linesIncrement = (int) (visibleArea.height / lineHeight * mult);
        editor.getScrollingModel().scrollVertically(visibleArea.y - visibleArea.y % lineHeight - linesIncrement * lineHeight);
        int lineShift = -linesIncrement;
        editor.getCaretModel().moveCaretRelatively(0, lineShift, isWithSelection, editor.isColumnMode(), true);
    }

    // https://github.com/JetBrains/intellij-community/blob/5107d68347f99578759f406fb128bb0907b820ea/platform/platform-impl/src/com/intellij/openapi/editor/actions/EditorActionUtil.java#L752
    public static void moveCaretPageDown(@NotNull Editor editor, boolean isWithSelection, float mult) {
        int lineHeight = editor.getLineHeight();
        Rectangle visibleArea = getVisibleArea(editor);
        int linesIncrement = (int) (visibleArea.height / lineHeight * mult);
        int allowedBottom = ((EditorEx) editor).getContentSize().height - visibleArea.height;
        editor.getScrollingModel().scrollVertically(
                Math.min(allowedBottom, visibleArea.y - visibleArea.y % lineHeight + linesIncrement * lineHeight));
        editor.getCaretModel().moveCaretRelatively(0, linesIncrement, isWithSelection, editor.isColumnMode(), true);
    }
}
