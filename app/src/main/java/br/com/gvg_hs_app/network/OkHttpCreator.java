package br.com.gvg_hs_app.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpCreator{
    private OkHttpClient.Builder httpClient;

    public OkHttpCreator(){
        this.httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor(){

            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException{
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("", "")
                        .header("", "")
                        .build();
                return chain.proceed(request);
            }
        });
    }

    public OkHttpClient.Builder getHttpClient(){
        return this.httpClient;
    }
}
