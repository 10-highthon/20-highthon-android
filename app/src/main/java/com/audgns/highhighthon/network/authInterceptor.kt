package com.audgns.highhighthon.network

import android.content.Context
import com.audgns.highhighthon.util.getQrScanUserId
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val userId = getQrScanUserId(context)

        val requestBuilder = chain.request().newBuilder()
        userId?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}
