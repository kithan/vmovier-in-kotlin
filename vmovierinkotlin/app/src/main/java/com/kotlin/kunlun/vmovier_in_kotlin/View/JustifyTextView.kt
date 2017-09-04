package com.kotlin.kunlun.vmovier_in_kotlin.View;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ccheng on 3/18/14.
 */
class JustifyTextView(context: Context?, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    var mLineY: Float = 0f
    var mViewWidth: Int = 0
    //
//
//
    override fun onDraw(canvas: Canvas) {
        mViewWidth = measuredWidth
        mLineY += (textSize * 1.5f)
        for (i in 0..layout.lineCount) {
            val lineStart: Int = layout.getLineStart(i)
            val lineEnd: Int = layout.getLineEnd(i)
            val line = text.substring(lineStart, lineEnd)

            val width: Float = StaticLayout.getDesiredWidth(text, lineStart, lineEnd, paint)
            if (needScale(line) && i < layout.lineCount - 1) {
                drawScaledText(canvas, lineStart, line, width);
            } else {
                canvas.drawText(line, 0f, mLineY, paint);
            }
            mLineY += lineHeight
        }
    }

    fun drawScaledText(canvas: Canvas, lineStart: Int, line: String, lineWidth: Float) {
        var x = 0f
        var cLine: String=""
        if (isFirstLineOfParagraph(lineStart, line)) {
            var blanks = "  ";
            canvas.drawText(blanks, x, mLineY, paint)
            var bw = StaticLayout.getDesiredWidth(blanks, paint)
            x += bw
            cLine = line.substring(3)
        }

        var d = (mViewWidth - lineWidth) / cLine.length - 1
        for (i in 0..cLine.length) {
            var c = cLine[i] as String
            var cw = StaticLayout.getDesiredWidth(c, paint)
            canvas.drawText(c, x, mLineY, paint)
            x += cw + d
        }
    }

    fun isFirstLineOfParagraph(lineStart: Int, line: String): Boolean {
        return line.length > 3 && line[0] == ' ' && line[1] == ' '
    }

    fun needScale(line: String): Boolean {
        if (line.length == 0) {
            return false;
        } else {
            return line[line.length - 1] != '\n'
        }
    }

}
