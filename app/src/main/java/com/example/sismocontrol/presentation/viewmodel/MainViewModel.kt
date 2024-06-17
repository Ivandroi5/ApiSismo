package com.example.sismocontrol.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sismocontrol.data.model.Sismo
import com.example.sismocontrol.data.network.SismoApiService
import com.example.sismocontrol.data.network.response.Properties
import com.example.sismocontrol.data.network.response.SismoResponse
import com.example.sismocontrol.domain.SismoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val sismoUseCase: SismoUseCase): ViewModel() {

    private val _sismosList = MutableLiveData<MutableList<Sismo>>()


    val sismosListLV: LiveData<MutableList<Sismo>>
        get() = _sismosList


    init{
        viewModelScope.launch {
            _sismosList.value = sismoUseCase.getAllEqForHour()
        }
    }


}