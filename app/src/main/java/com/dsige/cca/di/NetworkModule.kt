package com.dsige.cca.di

import com.google.gson.GsonBuilder
import com.dsige.cca.BuildConfig
import com.dsige.cca.data.network.IApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private var instance: Retrofit? = null

    @Provides
    internal fun provideRetrofit(
        gsonConverter: GsonConverterFactory,
        rxJava: RxJava3CallAdapterFactory,
        client: OkHttpClient
    ): Retrofit = instance
        ?: synchronized(this) {
            val retrofit by lazy {
                Retrofit.Builder().addConverterFactory(gsonConverter)
                    .addCallAdapterFactory(rxJava)
                    .client(client).baseUrl(BuildConfig.BASE_URL).build()
            }
            instance = retrofit
            retrofit
        }


    @Provides
    internal fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    internal fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    @Provides
    internal fun providesRxJavaCallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    @Provides
    internal fun provideService(retrofit: Retrofit): IApiClient {
        return retrofit.create(IApiClient::class.java)
    }
}