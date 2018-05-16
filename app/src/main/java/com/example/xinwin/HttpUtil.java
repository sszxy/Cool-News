package com.example.xinwin;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 张翔宇 on 2018/4/8.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
