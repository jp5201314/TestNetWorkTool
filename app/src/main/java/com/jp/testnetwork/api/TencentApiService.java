package com.jp.testnetwork.api;


import com.jp.networkarch.converter.ConverterFormat;
import com.jp.networkarch.converter.ResponseConverter;
import com.jp.testnetwork.bean.CookBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @ProjectName : NetWorkArchTools
 * @Author : Jason
 * @Time : 2020/8/22 11:37
 * @Description : 文件描述
 */
public interface TencentApiService {


    @GET("/cook/queryid")
    @ResponseConverter(format = ConverterFormat.XML)
    Observable<CookBean> getCook(@Query("id") int id,
                                 @Query("dtype") String dtype,
                                 @Query("key") String key);
}
