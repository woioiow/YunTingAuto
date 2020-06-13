package com.shinyv.cnr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.umeng.analytics.MobclickAgent;


/**
 * @Package: com.shinyv.cnr
 * @Description:
 * @Author: Maclay
 * @Date: 2020/5/25 11:38
 */
abstract public class BaseAutoActivity extends AppCompatActivity {

    public int duration = 0;
    public static final int MSG_WHAT_DO_NEXT = 0;

    public AppCompatImageView image;
    public TextView textView;

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_WHAT_DO_NEXT: {
                    duration--;
                    if (duration <= 0) {
                        nextAction();
                    } else {
                        sendEmptyMessageDelayed(MSG_WHAT_DO_NEXT, 1000);
                        updateText();
                    }
                }
            }
        }
    };

    private void nextAction() {
        JumpUtils.doNextIntent(this);
        if (needFinish()) {
            finish();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        System.out.println("minglog--device model "+ Build.MODEL
        +" brand "+Build.BRAND);
//        UMConfigure.init(this, "5eaa8d23570df3a3240003ed", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
//        UMConfigure.setLogEnabled(true); //关闭友盟log


//        MobclickAgent.UMAnalyticsConfig.class

        setContentView(getLayoutId());
        image = (AppCompatImageView) findViewById(R.id.image);
        textView = (TextView) findViewById(R.id.textView);
//        image.setImageResource(getImageId());
        initIntent(getIntent());


        MobclickAgent.setDebugMode(true);
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initIntent(intent);
    }

    public void initIntent(Intent intent) {
        if (intent != null) {
            duration = intent.getIntExtra("data", -1);
            if (duration > 0) {
                updateText();
                handler.sendEmptyMessageDelayed(MSG_WHAT_DO_NEXT, 1000);
            } else {
                textView.setText("延续" + "\n" + JumpUtils.pathStr);
            }
        }
    }

    public void updateText() {
        textView.setText(duration + "\n" + JumpUtils.pathStr);
    }

    private int getLayoutId() {
        return R.layout.activity_auto;
    }

    private String getText() {
        return getClass().getSimpleName();
    }

    public void setBg(int rid) {
//        image.setImageResource(rid);
    }

    public abstract int getImageId();

    public long getDurTime() {
        return duration;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeMessages(MSG_WHAT_DO_NEXT);
            handler.removeCallbacks(null);
            handler = null;
        }
    }




    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.toString());
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.toString());
        MobclickAgent.onPause(this);
    }



    public boolean needFinish() {
        return false;
    }
}
