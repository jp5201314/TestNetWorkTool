package com.jp.testnetwork;

import android.app.Application;

import com.jp.testnetwork.api_manager.TencentRetrofitManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

/**
 * @ProjectName : NetWorkArchTools
 * @Author : Jason
 * @Time : 2020/8/22 10:52
 * @Description : 文件描述
 */
public class NetApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TencentRetrofitManager.getInstance().init(1, () -> {
            HashMap hashMap = new HashMap();
            hashMap.put("key","value");
            return hashMap ;
        });
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
