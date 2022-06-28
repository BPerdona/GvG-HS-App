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
                        .header("X-RapidAPI-Key", "8a50b37af0mshaac729cca51fe47p12c76ajsnbccbb1fe4915")
                        .header("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com")
                        .build();
                return chain.proceed(request);
            }
        });
    }

    public OkHttpClient.Builder getHttpClient(){
        return this.httpClient;
    }
}
