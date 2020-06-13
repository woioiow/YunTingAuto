package com.shinyv.cnr;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * @Package: com.shinyv.cnr
 * @Description:
 * @Author: Maclay
 * @Date: 2020/5/25 11:38
 */
public class StartActivity_ extends BaseAutoActivity {
// javax.net.ssl.SSLHandshakeException: com.android.org.bouncycastle.jce.exception.ExtCertPathValidatorException
    //MobclickAgent: SSLHandshakeException, Failed to send message.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestPermissions();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        JumpUtils.initIntents(this);
        duration = 5;
        updateText();
        handler.sendEmptyMessageDelayed(MSG_WHAT_DO_NEXT, 1000);
    }

    private String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,

    };

    private void requestPermissions() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                ) {
            ActivityCompat.requestPermissions(this, permissions, 10086);
        }else
        {
        }
    }
    @Override
    public void initIntent(Intent intent) {
    }

    @Override
    public int getImageId() {
        return R.drawable.bg_start;
    }

    @Override
    public boolean needFinish() {
        return true;
    }
}
