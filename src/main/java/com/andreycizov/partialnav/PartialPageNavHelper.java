package com.andreycizov.partialnav;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.util.SystemProperties;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class PartialPageNavHelper {
    //  https://github.com/JetBrains/intellij-community/blob/9c78db8af09c69c7aba7268c650449dd423422e2/platform/platform-impl/src/com/intellij/openapi/editor/actions/EditorActionUtil.java#L791
    static Rectangle getVisibleArea(@NotNull Editor editor) {
        return SystemProperties.isTrueSmoothScrollingEnabled() ? editor.getScrollingModel().getVisibleAreaOnScrollingFinished()
                : editor.getScrollingModel().getVisibleArea();
    }

    // https://github.com/JetBrains/intellij-community/blob/9c78db8af09c69c7aba7268c650449dd423422e2/platform/platform-impl/src/com/intellij/openapi/editor/actions/EditorActionUtil.java#L743
    public static void moveCaretPageUp(@NotNull Editor editor, boolean isWithSelection, float mult) {
        int lineHeight = editor.getLineHeight();
        Rectangle visibleArea = getVisibleArea(editor);
        int linesIncrement = (int) (visibleArea.height / lineHeight * mult);
        if (!editor.getSettings().isRefrainFromScrolling()) {
            int scrollOffset = visibleArea.y - visibleArea.y % lineHeight - linesIncrement * lineHeight;
            editor.getScrollingModel().scrollVertically(scrollOffset);
        }
        int lineShift = -linesIncrement;
        editor.getCaretModel().moveCaretRelatively(0, lineShift, isWithSelection, editor.isColumnMode(), true);
    }

    // https://github.com/JetBrains/intellij-community/blob/9c78db8af09c69c7aba7268c650449dd423422e2/platform/platform-impl/src/com/intellij/openapi/editor/actions/EditorActionUtil.java#L752
    public static void moveCaretPageDown(@NotNull Editor editor, boolean isWithSelection, float mult) {
        int lineHeight = editor.getLineHeight();
        Rectangle visibleArea = getVisibleArea(editor);
        int linesIncrement = (int) (visibleArea.height / lineHeight * mult);
        int allowedBottom = ((EditorEx) editor).getContentSize().height - visibleArea.height;
        if (!editor.getSettings().isRefrainFromScrolling()) {
            int scrollOffset = Math.min(allowedBottom, visibleArea.y - visibleArea.y % lineHeight + linesIncrement * lineHeight);
            editor.getScrollingModel().scrollVertically(scrollOffset);
        }
        editor.getCaretModel().moveCaretRelatively(0, linesIncrement, isWithSelection, editor.isColumnMode(), true);
    }
}
