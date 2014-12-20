package com.bytoaster.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by baptiste on 12/18/14.
 */
public class LetterCircleView extends TextView {

    private static final int DEFAULT_CONTENT_COLOR =
            Color.parseColor("#33B5E5");
    private static final int DEFAULT_SHADOW_SIZE = 0;
    private static final int DEFAULT_SHADOW_COLOR =
            Color.parseColor("#E1E1E1");
    private static final int DEFAULT_BORDER_SIZE = 0;
    private static final int DEFAULT_BORDER_COLOR =
            Color.parseColor("#FFFFFFFF");
    private static final int DEFAULT_TEXT_ALPHA = 255;

    private int contentColor = DEFAULT_CONTENT_COLOR;
    private int shadowSize = DEFAULT_SHADOW_SIZE;
    private int shadowColor = DEFAULT_SHADOW_COLOR;
    private int borderSize = DEFAULT_BORDER_SIZE;
    private int borderColor = DEFAULT_BORDER_COLOR;
    private int textAlpha = DEFAULT_TEXT_ALPHA;

    private float scale;
    private int centerX;
    private int centerY;
    private float totalRadius;
    private float centerYShadow;
    private float shadowRadius;
    private float borderRadius;
    private float contentRadius;

    private Paint contentPaint;
    private Paint shadowPaint;
    private Paint borderPaint;

    public LetterCircleView(final Context context) {
        super(context);
        init(context, null);
    }

    public LetterCircleView(final Context context,
                            final AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LetterCircleView(final Context context,
                            final AttributeSet attrs,
                            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onSizeChanged(final int w,
                                 final int h,
                                 final int oldW,
                                 final int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        centerX = w/2;
        centerY = h/2;

        centerYShadow = centerY + shadowSize;

        totalRadius = Math.min(centerX, centerY);
        borderRadius = totalRadius - shadowSize;
        contentRadius = totalRadius - shadowSize - borderSize;
        shadowRadius = borderRadius;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        drawShadow(canvas);
        drawBorder(canvas);
        drawContent(canvas);
        drawText();
        super.onDraw(canvas);
    }

    private void drawShadow(final Canvas canvas) {
        shadowPaint.setColor(shadowColor);
        canvas.drawCircle(centerX, centerYShadow, shadowRadius, shadowPaint);
    }

    private void drawBorder(final Canvas canvas) {
        borderPaint.setColor(borderColor);
        canvas.drawCircle(centerX, centerY, borderRadius, borderPaint);
    }

    private void drawContent(final Canvas canvas) {
        contentPaint.setColor(contentColor);
        canvas.drawCircle(centerX, centerY, contentRadius, contentPaint);
    }

    private void drawText() {
        setTextColor(getTextColors().withAlpha(textAlpha));
    }

    private void init(final Context context,
                      final AttributeSet attrs) {
        setGravity(Gravity.CENTER);

        scale = getResources().getDisplayMetrics().density;

        shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shadowPaint.setStyle(Paint.Style.FILL);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.FILL);

        contentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        contentPaint.setStyle(Paint.Style.FILL);

        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(
                    attrs,
                    R.styleable.LetterCircleView);

            getAttrContentColor(a);
            getAttrShadowColor(a);
            getAttrBorderColor(a);
            // TODO: proper conversion dp to int
            getAttrBorderDimen(a);
            getAttrShadowDimen(a);
            getAttrTextAlpha(a);
            a.recycle();
        }
    }

    private void getAttrContentColor(final TypedArray array) {
        contentColor = array.getColor(
                R.styleable.LetterCircleView_contentColor,
                DEFAULT_CONTENT_COLOR);
    }

    private void getAttrBorderColor(final TypedArray array) {
        borderColor = array.getColor(
                R.styleable.LetterCircleView_borderColor,
                DEFAULT_BORDER_COLOR);
    }

    private void getAttrBorderDimen(final TypedArray array) {
        borderSize = (int)array.getDimension(
                R.styleable.LetterCircleView_borderSize,
                DEFAULT_BORDER_SIZE);
    }

    private void getAttrShadowColor(final TypedArray array) {
        shadowColor = array.getColor(
                R.styleable.LetterCircleView_shadowColor,
                DEFAULT_SHADOW_COLOR);
    }

    private void getAttrShadowDimen(final TypedArray array) {
        shadowSize = (int)array.getDimension(
                R.styleable.LetterCircleView_shadowSize,
                DEFAULT_SHADOW_SIZE);
    }

    private void getAttrTextAlpha(final TypedArray array) {
        textAlpha = array.getInteger(
                R.styleable.LetterCircleView_textAlpha,
                DEFAULT_TEXT_ALPHA);
    }

    public void setContentColor(final int color) {
        this.contentColor = color;
        invalidate();
    }

    public void setBorderColor(final int color) {
        this.borderColor = color;
        invalidate();
    }

    public void setShadowColor(final int color) {
        this.shadowColor = color;
        invalidate();
    }

    public void setShadowSize(final int shadowSizeInDp) {
        this.shadowSize = convertDpToPx(shadowSizeInDp);
        invalidate();
    }

    public void setBorderSize(final int borderSizeInDp) {
        this.borderSize = convertDpToPx(borderSizeInDp);
        invalidate();
    }

    public void setTextAlpha(final int textAlpha) {
        this.textAlpha = textAlpha;
        invalidate();
    }

    private int convertDpToPx(final int valueInDp) {
        return (int) (valueInDp * scale + 0.5f);
    }
}