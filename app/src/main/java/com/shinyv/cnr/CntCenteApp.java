package com.shinyv.cnr;

import android.app.Application;


/**
 * @Package: com.shinyv.cnr
 * @Description:
 * @Author: Maclay
 * @Date: 2020/5/25 11:34
 */
public class CntCenteApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initUmeng();
    }

    private void initUmeng() {
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数3:Push推送业务的secret
         */
//        UMConfigure.init(getApplicationContext(), "", "", UMConfigure.DEVICE_TYPE_PHONE, "");
    }
}
