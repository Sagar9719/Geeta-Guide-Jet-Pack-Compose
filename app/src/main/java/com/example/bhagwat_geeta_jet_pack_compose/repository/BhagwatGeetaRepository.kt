package com.example.bhagwat_geeta_jet_pack_compose.repository

import com.example.bhagwat_geeta_jet_pack_compose.dataModel.BhagwatGeeta
import com.example.bhagwat_geeta_jet_pack_compose.retrofit.BhagwatGeetaService
import javax.inject.Inject

class BhagwatGeetaRepository @Inject constructor(private val bhagwatGeetaService: BhagwatGeetaService) {

    suspend fun getBhagwatGeeta(): List<BhagwatGeeta> {
        return bhagwatGeetaService.getBhagwatGeeta(18)
    }
}