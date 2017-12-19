package com.zhuandian.rotateloadingtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * desc：字体周边圆环旋转
 * author：xiedong
 * date：2017/12/19.
 */
public class RotateLoadingTextView extends FrameLayout {
    private Context mContext;
    private ImageView mRotateView;
    private TextView textView;
    private String textMsg;

    public RotateLoadingTextView(@NonNull Context context) {
        super(context, null);
    }

    public RotateLoadingTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.RotateLoadingTextView);
        textMsg = ta.getString(R.styleable.RotateLoadingTextView_text);
        ta.recycle();
        initView();
    }

    private void initView() {
        View view = View.inflate(mContext, R.layout.rotate_loading_textview, this);
        mRotateView = (ImageView) view.findViewById(R.id.loading_view);
        textView = (TextView) view.findViewById(R.id.tv_text);
        textView.setText(textMsg);
        Animation rotateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_loading);
        rotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(mContext, android.R.anim.accelerate_decelerate_interpolator));
        mRotateView.setAnimation(rotateAnimation);
        mRotateView.startAnimation(rotateAnimation);
    }

    private void setText(String str) {
        textView.setText(str);
    }
}
