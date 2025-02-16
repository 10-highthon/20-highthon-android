package com.audgns.highhighthon.network

import android.content.Context
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://17f6-2001-2d8-e1fb-4088-b802-a130-f422-3868.ngrok-free.app"

object RetrofitClient {
    private lateinit var okHttpClient: OkHttpClient

    // Context를 받아서 OkHttpClient를 초기화하는 init 함수
    fun init(context: Context) {
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor { message -> Log.v("Http", message) }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

    // Retrofit 초기화
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)  // 초기화된 okHttpClient 사용
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}
