package com.example.lex.hhmini.retrofit

import okhttp3.Interceptor
import okhttp3.Response


class UserAgentInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithUserAgent = originalRequest.newBuilder()
                .header("User-Agent", "Android")
                .build()
        return chain.proceed(requestWithUserAgent)
    }
}