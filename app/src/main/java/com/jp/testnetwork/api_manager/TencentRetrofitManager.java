package com.jp.testnetwork.api_manager;

import com.jp.networkarch.base_bean.BaseResponse;
import com.jp.networkarch.common_interceptor.INetworkRequireInfo;
import com.jp.networkarch.error_handle.ExceptionHandler;
import com.jp.networkarch.net.IRetrofitManager;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;

/**
 * @ProjectName : NetWorkArchTools
 * @Author : Jason
 * @Time : 2020/8/21 23:52
 * @Description : 文件描述
 */
public class TencentRetrofitManager extends IRetrofitManager {

    private static volatile TencentRetrofitManager instance;
    private static int mNetType = 1;
    public static TencentRetrofitManager getInstance(){
        if (instance==null){
            synchronized (TencentRetrofitManager.class){
                if (instance==null){
                    instance = new TencentRetrofitManager();
                }
            }
        }
        return instance;
    }

    public void init(int netType, INetworkRequireInfo iNetworkRequireInfo){
        mNetType = netType;
        init(iNetworkRequireInfo,getCurEnvironment());
    }

    public  void init(int netType){
        mNetType = netType;
        init(null,getCurEnvironment());
    }
    private TencentRetrofitManager() {
        super();
    }

    public static <T> T getService(Class<T> tClass) {

        return getRetrofit(tClass).create(tClass);
    }

    @Override
    protected Interceptor addInterceptor() {
        return null;
    }

    @Override
    protected <T> Function<T, T> getAppErrorHandle() {
        return new Function<T, T>() {
            @Override
            public T apply(T response) throws Exception {
                if (response instanceof BaseResponse && ((BaseResponse) response).code != 0) {
                    ExceptionHandler.ServerException exception =  new ExceptionHandler.ServerException();
                    exception.errorCode = ((BaseResponse) response).code;
                    exception.errorMsg = ((BaseResponse) response).msg!=null?
                            ((BaseResponse) response).msg:"";
                    throw exception;
                }
                return response;
            }
        };
    }

    @Override
    public  String getCurEnvironment() {
        String curBaseUrl = "";
        switch (mNetType) {
            case 1:
                curBaseUrl = "http://apis.juhe.cn";
                break;
            case 2:
                curBaseUrl = "http://www.souhu.com";
                break;
        }
        return curBaseUrl;
    }
}
