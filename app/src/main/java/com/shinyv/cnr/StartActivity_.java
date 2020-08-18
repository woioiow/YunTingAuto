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
        duration = 6;
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


    private String  getDeviceName()
    {
        StringBuffer sb = new StringBuffer();
        String product = "产品 : " + android.os.Build.PRODUCT;
        sb.append(product+"\n");
        String cpu= " CPU_ABI : " + android.os.Build.CPU_ABI;
        String tags= " 标签 : " + android.os.Build.TAGS;
        String version_codes_base= " VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE;
        String model= " 型号 : " + android.os.Build.MODEL;
        sb.append(model+"\n");
        String sdk= " SDK : " + android.os.Build.VERSION.SDK;
        String androidversion= " Android 版本 : " + android.os.Build.VERSION.RELEASE;
        String device= " 驱动 : " + android.os.Build.DEVICE;
        String display= " DISPLAY: " + android.os.Build.DISPLAY;
        String brand= " 品牌 : " + android.os.Build.BRAND;
        sb.append(brand+"\n");
        String board= " 基板 : " + android.os.Build.BOARD;
        sb.append(board+"\n");
        String sign= " 设备标识 : " + android.os.Build.FINGERPRINT;
        String id= " 版本号 : " + android.os.Build.ID;
        String maker= " 制造商 : " + android.os.Build.MANUFACTURER;
        String user= " 用户 : " + android.os.Build.USER;
        String cpu2=" CPU_ABI2 : "+android.os.Build.CPU_ABI2;
        String  hardware=" 硬件 : "+ android.os.Build.HARDWARE;
        sb.append(hardware+"\n");
        String host=" 主机地址 :"+android.os.Build.HOST;
        String unknown =" 未知信息 : "+android.os.Build.UNKNOWN;
        String type=" 版本类型 : "+android.os.Build.TYPE;
        String time =" 时间 : "+String.valueOf(android.os.Build.TIME);
        String radio =" Radio : "+android.os.Build.RADIO;
        String serial=" 序列号 : "+android.os.Build.SERIAL;
        System.out.println(" sb.tostring "+sb.toString());
        return  sb.toString();

    }
}
