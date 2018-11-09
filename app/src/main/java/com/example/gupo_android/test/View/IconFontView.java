package com.example.gupo_android.test.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

public class IconFontView extends android.support.v7.widget.AppCompatTextView {

    public IconFontView(Context context) {
        super(context);
        setIconFont(context);
    }

    public IconFontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setIconFont(context);
    }

    public IconFontView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setIconFont(context);
    }



    private void setIconFont(Context context) {
        setIncludeFontPadding(false);
        Typeface iconFont = CreateTypeFace.getIconFont(context);
        //if (iconFont != null) {
        setTypeface(iconFont);
        //}
    }

    private static class CreateTypeFace {
        private static Typeface iconFont;

        private static Typeface getIconFont(Context context) {
            if (iconFont == null) {
                iconFont = Typeface.createFromAsset(context.getAssets(), "iconfont.ttf");
            }
            return iconFont;
        }
    }
}
