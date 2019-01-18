package com.example.gupo_android.test.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.gupo_android.test.Adapter.MenuAdapter;
import com.example.gupo_android.test.Bean.MenuBean;
import com.example.gupo_android.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : whx
 * @date : 2019/1/16 14:20
 */
public class MyMenu extends PopupWindow {

    private Context context;

    public MyMenu(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MyMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MyMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private RecyclerView rvMenu;
    private List<MenuBean> menuBeans;
    private ImageView iv;
    private MenuAdapter menuAdapter;
    private LinearLayout contentView;
    private int height;
    private int width;
    private LinearLayout.LayoutParams ivLP;

    public void setMenuBeans(List<MenuBean> menuBeans) {
        this.menuBeans = menuBeans;
        menuAdapter.setMenuBeans(menuBeans);
        menuAdapter.notifyDataSetChanged();
    }

    class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

        public static final int HORIZONTAL = 0;
        public static final int VERTICAL = 1;
        private static final String TAG = "DividerItem";
        private final int[] ATTRS = new int[]{16843284};
        private Drawable mDivider;
        private int mOrientation;
        private final Rect mBounds = new Rect();

        public MyDividerItemDecoration(Context context, int orientation) {
            TypedArray a = context.obtainStyledAttributes(ATTRS);
            this.mDivider = a.getDrawable(0);
            if (this.mDivider == null) {
                Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
            }

            a.recycle();
            this.setOrientation(orientation);
        }

        public void setOrientation(int orientation) {
            if (orientation != 0 && orientation != 1) {
                throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
            } else {
                this.mOrientation = orientation;
            }
        }

        public void setDrawable(@NonNull Drawable drawable) {
            if (drawable == null) {
                throw new IllegalArgumentException("Drawable cannot be null.");
            } else {
                this.mDivider = drawable;
            }
        }

        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            if (parent.getLayoutManager() != null && this.mDivider != null) {
                if (this.mOrientation == 1) {
                    this.drawVertical(c, parent);
                } else {
                    this.drawHorizontal(c, parent);
                }

            }
        }

        private void drawVertical(Canvas canvas, RecyclerView parent) {
            canvas.save();
            final int left;
            final int right;
            //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
            if (parent.getClipToPadding()) {
                left = parent.getPaddingLeft();
                right = parent.getWidth() - parent.getPaddingRight();
                canvas.clipRect(left, parent.getPaddingTop(), right,
                        parent.getHeight() - parent.getPaddingBottom());
            } else {
                left = 0;
                right = parent.getWidth();
            }

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                parent.getDecoratedBoundsWithMargins(child, mBounds);
                final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
                final int top = bottom - mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            canvas.restore();
        }

        private void drawHorizontal(Canvas canvas, RecyclerView parent) {
            canvas.save();
            int top;
            int bottom;
            if (parent.getClipToPadding()) {
                top = parent.getPaddingTop();
                bottom = parent.getHeight() - parent.getPaddingBottom();
                canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
            } else {
                top = 0;
                bottom = parent.getHeight();
            }

            int childCount = parent.getChildCount();

            for (int i = 0; i < childCount; ++i) {
                View child = parent.getChildAt(i);
                parent.getLayoutManager().getDecoratedBoundsWithMargins(child, this.mBounds);
                int right = this.mBounds.right + Math.round(child.getTranslationX());
                int left = right - this.mDivider.getIntrinsicWidth();
                this.mDivider.setBounds(left, top, right, bottom);
                this.mDivider.draw(canvas);
            }

            canvas.restore();
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (this.mDivider == null) {
                outRect.set(0, 0, 0, 0);
            } else {
                if (this.mOrientation == 1) {
                    outRect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
                } else {
                    outRect.set(0, 0, this.mDivider.getIntrinsicWidth(), 0);
                }

            }
        }

    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        contentView = (LinearLayout) inflater.inflate(R.layout.menu_popup_window, null);

        rvMenu = contentView.findViewById(R.id.rvMenu);
        rvMenu.setLayoutManager(new LinearLayoutManager(context));
        rvMenu.setBackgroundResource(R.drawable.rc_corner_popup_dialog_style);
        //设置分割线
        rvMenu.addItemDecoration(new MyDividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        iv = contentView.findViewById(R.id.iv);
        ivLP = (LinearLayout.LayoutParams) iv.getLayoutParams();
        ivLP.setMargins(15, 0, 15, 0);

        menuBeans = new ArrayList<>();
        menuAdapter = new MenuAdapter(context, menuBeans);
        contentView.removeView(iv);
        rvMenu.setAdapter(menuAdapter);

        height = context.getResources().getDisplayMetrics().heightPixels;
        width = context.getResources().getDisplayMetrics().widthPixels;
        // 设置SelectPicPopupWindow的View
        this.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(width / 3 - 30);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupAnimation);
    }


    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(final View parent) {
        parent.setOnTouchListener(new View.OnTouchListener() {
            boolean isDown = false;
            boolean isEnd = false;
            int viewHeight;
            int viewWidth;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        contentView.measure(w, h);
                        viewHeight = contentView.getMeasuredHeight();
                        viewWidth = contentView.getMeasuredWidth();
                        float h1 = event.getRawY() + viewHeight;
                        float w1 = event.getRawX() + viewWidth;
                        isDown = h1 < height;
                        isEnd = w1 < width;
                        if (!MyMenu.this.isShowing()) {
                            int x;
                            int y;
                            ivLP.gravity = isEnd ? Gravity.START : Gravity.END;
                            iv.setLayoutParams(ivLP);
                            if (isDown) {
                                iv.setImageResource(R.mipmap.ys_icon_b_jj_up);
                                contentView.addView(iv, 0);
                                x = (int) (isEnd ? event.getRawX() - iv.getWidth() : event.getRawX() - contentView.getWidth() + iv.getWidth());
                                y = (int) (event.getRawY() + event.getY());
                            } else {
                                iv.setImageResource(R.mipmap.ys_icon_b_jj_down);
                                contentView.addView(iv, 1);
                                x = (int) (isEnd ? event.getRawX() - iv.getWidth() : event.getRawX() - contentView.getWidth() + iv.getWidth());
                                y = (int) (event.getRawY() - viewHeight - event.getY());
                            }
                            MyMenu.this.showAtLocation(parent, Gravity.TOP | Gravity.START, x, y);
                        } else {
                            MyMenu.this.dismiss();
                        }
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void dismiss() {
        super.dismiss();
        contentView.removeView(iv);
    }
}
