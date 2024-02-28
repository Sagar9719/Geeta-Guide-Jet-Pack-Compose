package com.example.bhagwat_geeta_jet_pack_compose.di

import com.example.bhagwat_geeta_jet_pack_compose.retrofit.BhagwatGeetaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://bhagavad-gita3.p.rapidapi.com/"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun retrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideBhagwatGeetaService(retrofit: Retrofit) : BhagwatGeetaService{
        return retrofit.create(BhagwatGeetaService::class.java)
    }

    @Provides
    fun provideOkHttp(networkInterceptor: NetworkInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .build()
    }

    @Provides
    fun provideNetworkInterceptor() : NetworkInterceptor{
        return NetworkInterceptor()
    }
}