package com.example.bhagwat_geeta_jet_pack_compose.di

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder: Request.Builder = chain.request().newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        requestBuilder.header(
            "X-RapidAPI-Key",
            "a960c7cc3dmsh0c07768b64ebe28p104246jsna2229aa97760"
        )
        requestBuilder.header("X-RapidAPI-Host", "bhagavad-gita3.p.rapidapi.com")
        return chain.proceed(requestBuilder.build())
    }
}