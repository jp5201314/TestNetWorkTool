package com.jp.testnetwork;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jp.networkarch.error_handle.ExceptionHandler;
import com.jp.networkarch.observer.BaseObserver;
import com.jp.testnetwork.api.TencentApiService;
import com.jp.testnetwork.api_manager.TencentRetrofitManager;
import com.jp.testnetwork.bean.CookBean;
import com.orhanobut.logger.Logger;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TencentRetrofitManager.getService(TencentApiService.class)
                .getCook(1,"json","a4f9bccd568918677dbd43f2f268d0f5")
                .compose(TencentRetrofitManager.getInstance()
        .applySchedulers(new BaseObserver<CookBean>() {
            @Override
            public void addDisposable(Disposable disposable) {

            }
            @Override
            public void onSuccess(CookBean cookBean) {
                Logger.i(cookBean.toString());
            }

            @Override
            public void onFailure(ExceptionHandler.ResponseThrowable throwable) {
                Logger.i(throwable.getErrorMsg());
            }
        }));

    }
}