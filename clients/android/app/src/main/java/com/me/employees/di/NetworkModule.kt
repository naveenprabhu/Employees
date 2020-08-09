package com.me.employees.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.me.employees.interceptor.ForceCacheInterceptor
import com.me.employees.service.EmployeeService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module()
class NetworkModule {
    val BASE_URL = "http://10.0.2.2:5000/"


    @Singleton
    @Provides
    fun providesGson() : Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun providesCache(context: Context) : Cache{
        var cacheSize: Long = 10 * 1024 * 1024 //10 MB
        var httpCacheDirectory: File = File(context.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache, forceCacheInterceptor: ForceCacheInterceptor): OkHttpClient {
        var logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        var httpClient = OkHttpClient.Builder()
        httpClient.cache(cache)
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor(forceCacheInterceptor)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }


    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideEmployeeApiService(retrofit: Retrofit) : EmployeeService {
        return retrofit.create(EmployeeService::class.java)
    }


}