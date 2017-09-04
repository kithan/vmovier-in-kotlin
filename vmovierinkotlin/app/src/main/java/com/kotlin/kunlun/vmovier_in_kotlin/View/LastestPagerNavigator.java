package com.kotlin.kunlun.vmovier_in_kotlin.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import net.lucode.hackware.magicindicator.abs.IPagerNavigator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpb on 2017/8/12.
 */

public class LastestPagerNavigator extends View implements IPagerNavigator {


    private int mColor;
    private int mRectWidth, mRectHeight;
    private int mStrokeWidth;
    private int mSpacing;
    private int mCurrentIndex;
    private int mTotalCount;
    private Interpolator mStartInterpolator = new LinearInterpolator();

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<Rect> mPoints = new ArrayList<Rect>();
    private Rect mIndicatorX;
    private boolean mFollowTouch = false;    // 是否跟随手指滑动

    public LastestPagerNavigator(Context context) {
        super(context);
        init(context);
    }


    private void init(Context context) {
        mRectWidth = UIUtil.dip2px(context, 18);
        mRectHeight = UIUtil.dip2px(context, 2);
        mSpacing = UIUtil.dip2px(context, 2);
        mStrokeWidth = UIUtil.dip2px(context, 1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(mColor);
        drawRect(canvas);
        drawIndicator(canvas);
    }

    private void drawRect(Canvas canvas) {
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(Color.GRAY);
        for (int i = 0, j = mPoints.size(); i < j; i++) {
            canvas.drawRect(mPoints.get(i), mPaint);
        }
    }

    private void drawIndicator(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);

        if (mPoints.size() > 0) {
            canvas.drawRect(mIndicatorX, mPaint);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        prepareRects();
    }

    private void prepareRects() {
        mPoints.clear();
        if (mTotalCount > 0) {
            int centerSpacing = mRectWidth + mSpacing;
            int startX = getPaddingLeft();
            for (int i = 0; i < mTotalCount; i++) {
                Rect rect = new Rect(startX, getPaddingTop(), startX + mRectWidth, getPaddingTop() + mRectHeight);
                mPoints.add(rect);
                startX += centerSpacing  + mStrokeWidth * 2;
            }
            mIndicatorX = mPoints.get(mCurrentIndex);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }


    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = width;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = mTotalCount * (mRectWidth+mStrokeWidth * 2) + (mTotalCount - 1) * mSpacing + getPaddingLeft() + getPaddingRight();
                break;
            default:
                break;
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = height;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = mRectHeight + mStrokeWidth * 2 + getPaddingTop() + getPaddingBottom();
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int position) {
        mCurrentIndex = position % mTotalCount;
        if (!mFollowTouch) {
            mIndicatorX = mPoints.get(mCurrentIndex);
            invalidate();
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onAttachToMagicIndicator() {

    }

    @Override
    public void onDetachFromMagicIndicator() {

    }

    @Override
    public void notifyDataSetChanged() {
        prepareRects();
        invalidate();
    }



    public void setRectColor(int rectColor) {
        mColor = rectColor;
        invalidate();
    }


    public void setStrokeWidth(int strokeWidth) {
        mStrokeWidth = strokeWidth;
        invalidate();
    }

    public void setRectSpacing(int spacing) {
        mSpacing = spacing;
        prepareRects();
        invalidate();
    }

    public Interpolator getStartInterpolator() {
        return mStartInterpolator;
    }

    public void setStartInterpolator(Interpolator startInterpolator) {
        mStartInterpolator = startInterpolator;
        if (mStartInterpolator == null) {
            mStartInterpolator = new LinearInterpolator();
        }
    }


    public void setRectCount(int count) {
        mTotalCount = count;
    }


    public void setFollowTouch(boolean followTouch) {
        mFollowTouch = followTouch;
    }


}
