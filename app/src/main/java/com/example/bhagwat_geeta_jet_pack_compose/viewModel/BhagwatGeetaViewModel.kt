package com.example.bhagwat_geeta_jet_pack_compose.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bhagwat_geeta_jet_pack_compose.dataModel.BhagwatGeeta
import com.example.bhagwat_geeta_jet_pack_compose.repository.BhagwatGeetaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BhagwatGeetaViewModel @Inject constructor(private val repository : BhagwatGeetaRepository): ViewModel() {

    private val _bhagwatGeetas = MutableLiveData<List<BhagwatGeeta>>()
    val bhagwatGeetas: LiveData<List<BhagwatGeeta>> = _bhagwatGeetas

    fun fetchBhagwatGeeta(){
        viewModelScope.launch {
            try{
                _bhagwatGeetas.postValue(repository.getBhagwatGeeta())
            }
            catch (e: Exception){
                Log.d("Exception", "Exception Error")
            }
        }
    }
}