package com.zhuandian.rotateloadingtextview;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //第一种倒计时
        new Thread(new TiemerThread()).start();
        //第二种倒计时
        handler2.postDelayed(runnable, 1000);
        //第三种倒计时
        timer.schedule(timerTask, 1000, 1000);// 1s后执行task,经过1s再次执行
        //第四种倒计时
        countDownTimer.start();


    }


    /**
     * new CountDownTimer(10000, 1000)中，第一个参数表示总时间，第二个参数表示间隔时间。意思就是每隔一秒会回调一次方法onTick，然后10秒之后会回调onFinish方法。
     */
    CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            System.out.println("-----------"+millisUntilFinished / 1000 + "秒后结束第四种倒计时");
        }

        @Override
        public void onFinish() {
            System.out.println("-----------------结束第四种---------------------");
        }
    };
    /**
     * 定时器实现:Timer+TimerTask
     */
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            System.out.println("-----------第3种倒计时实现------------");
        }
    };


    /**
     * handler定时器使用postDelyed实现
     */
    Handler handler2 = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler2.postDelayed(this, 1000);
            System.out.println("-----------第2种倒计时实现------------");
        }
    };


    /**
     * handler定时器
     */
    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                System.out.println("-----------第一种倒计时实现------------");
            }
        }
    };

    class TiemerThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    handler1.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
