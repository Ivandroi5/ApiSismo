package com.example.sismocontrol.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sismocontrol.domain.SismoUseCase

class MainViewModelFactory(private val sismoUseCase: SismoUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(sismoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}