package com.owllearning.mobile.data.api;

import android.content.Context;
import android.view.PixelCopy;

import com.owllearning.mobile.BuildConfig;
import com.owllearning.mobile.utils.SessionManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ClientApi {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {
        if(retrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder();

                        SessionManager sessionManager = new SessionManager(context);
                        String token = sessionManager.getToken();

                        if(token != null && !token.isEmpty()) {
                            requestBuilder.header("Authorization", "Bearer " + token);
                        }

                        return chain.proceed(requestBuilder.build());
                    }) .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
