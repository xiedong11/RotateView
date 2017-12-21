package com.zhuandian.rotateloadingtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * desc：字体周边圆环旋转
 * author：xiedong
 * date：2017/12/19.
 */
public class RotateLoadingTextView extends FrameLayout {
    private Context mContext;
    private ImageView mRotateView;
    private TextView tvMsg;
    private String textMsg;
    private int timeDuring;
    private TextView tvDuring;

    public RotateLoadingTextView(@NonNull Context context) {
        super(context, null);
    }

    public RotateLoadingTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.RotateLoadingTextView);
        textMsg = ta.getString(R.styleable.RotateLoadingTextView_text);
        timeDuring = ta.getInt(R.styleable.RotateLoadingTextView_during, 3);
        ta.recycle();
        initView();
        initTimeCountDown(timeDuring);
    }

    private void initView() {
        View view = View.inflate(mContext, R.layout.rotate_loading_textview, this);
        mRotateView = (ImageView) view.findViewById(R.id.loading_view);
        tvMsg = (TextView) view.findViewById(R.id.tv_text);
        tvDuring = (TextView) view.findViewById(R.id.tv_time);
        tvMsg.setText(textMsg);
        Animation rotateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_loading);
        rotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(mContext, android.R.anim.accelerate_decelerate_interpolator));
        mRotateView.setAnimation(rotateAnimation);
        mRotateView.startAnimation(rotateAnimation);
    }

    private void initTimeCountDown(int during) {
        CountDownTimer countDownTimer = new CountDownTimer(during * 1000, 1000) {
            @Override
            public void onTick(long l) {
                tvDuring.setText(l / 1000 + "s");
            }

            @Override
            public void onFinish() {
                tvDuring.setText(0 + "s");
                Toast.makeText(mContext, "正在跳过广告闪屏页...", Toast.LENGTH_SHORT).show();
            }
        };
        countDownTimer.start();
    }

    /**
     * 设置提示文字
     *
     * @param str
     */
    private void setText(String str) {
        tvMsg.setText(str);
    }

    /**
     * 设置倒计时长
     *
     * @param during
     */
    private void setTimeDuring(int during) {
        this.timeDuring = during;
    }
}
