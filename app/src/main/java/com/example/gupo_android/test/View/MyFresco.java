package com.example.gupo_android.test.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

public class MyFresco extends SimpleDraweeView {

    private Matrix mMatrix;
    private float mScaleX = 1f;
    private float mScaleY = 1f;
    private int mViewWidth = -1;
    private int mViewHeight = -1;
    private RectF mDisplayRect = new RectF();
    private int mDegree = -1;

    public MyFresco(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        init();
    }

    public MyFresco(Context context) {
        super(context);
        init();
    }

    public MyFresco(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setViewInfo(int width, int Height) {
        mViewWidth = width;
        mViewHeight = Height;
    }

    private void init() {
        mMatrix = new Matrix();
        mMatrix.postScale(mScaleX, mScaleY);
    }

    /**
     * 缩放
     * @param scaleX 缩放倍数
     */
    public void setScale(float scaleX, float scaleY) {
        mScaleX = scaleX;
        mScaleY = scaleY;
        mMatrix.postScale(scaleX, scaleY);
        invalidate();
    }

    /**
     * 旋转
     * @param degree 角度
     */
    public void rotate(int degree) {
        if (mDegree == -1) {
            mDegree = degree;
            if (mDegree != 0) {
                mMatrix.postRotate(degree);
                invalidate();
                if (mDegree == 90) {
                    //旋转后图片超出边界，所以要再做平移
                    mMatrix.postTranslate(getRectWidth(), 0);
                } else if (mDegree == 180) {
                    mMatrix.postTranslate(getRectWidth(), getRectHieght());
                } else if (mDegree == 270) {
                    mMatrix.postTranslate(0, getRectHieght());
                }
            }
        } else {
            mDegree += degree;
            mMatrix.postRotate(degree); //getRectWidth是旋转后的width
            invalidate();
            mMatrix.postTranslate(getRectWidth(), 0);
        }
        invalidate();
    }

    /**
     * 还原设置
     */
    public void reset() {
        mScaleX = 1f;
        mScaleY = 1f;
        mMatrix = new Matrix();
        mMatrix.setScale(mScaleX, mScaleY);
        mViewWidth = -1;
        mViewHeight = -1;
        mDegree = -1;
    }

    /**
     * 获得旋转后超出边界的高度
     * @return
     */
    public float getRectHieght() {
        RectF displayRect = getDisplayRect(mMatrix);
        if (displayRect != null) {
            return displayRect.height();
        } else {
            return -1;
        }
    }

    /**
     * 获得旋转后超出边界的宽度
     * @return
     */
    public float getRectWidth() {
        RectF displayRect = getDisplayRect(mMatrix);
        if (displayRect != null) {
            return displayRect.width();
        } else {
            return -1;
        }
    }

    private RectF getDisplayRect(Matrix matrix) {
        if (mViewWidth == -1 || mViewHeight == -1) {
            return null;
        }
        mDisplayRect.set(0.0F, 0.0F, mViewWidth, mViewHeight);
        getHierarchy().getActualImageBounds(mDisplayRect);
        //将matrix映射到rectf
        matrix.mapRect(mDisplayRect);
        return mDisplayRect;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int save = canvas.save();
        canvas.concat(mMatrix);
        super.onDraw(canvas);
        canvas.restoreToCount(save);
    }
}