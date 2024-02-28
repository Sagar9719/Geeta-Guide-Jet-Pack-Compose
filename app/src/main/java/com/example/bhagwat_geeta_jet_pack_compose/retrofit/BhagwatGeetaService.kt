package com.example.bhagwat_geeta_jet_pack_compose.retrofit

import com.example.bhagwat_geeta_jet_pack_compose.dataModel.BhagwatGeeta
import dagger.Provides
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BhagwatGeetaService {
    @GET("v2/chapters/")
    suspend fun getBhagwatGeeta(@Query("limit") limit: Int): List<BhagwatGeeta>
}