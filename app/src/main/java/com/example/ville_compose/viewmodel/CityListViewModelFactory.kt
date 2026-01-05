package com.example.ville_compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ville_compose.repository.CityRepository

class CityListViewModelFactory (
    private val repository: CityRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) : T{
        return CityListViewModel(repository) as T
    }
}