package com.example.travelji.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelji.model.CardItemPojo
import com.example.travelji.repo.AppRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {

    val appRepo = AppRepo()

    private val _dataPlaces = MutableStateFlow<List<CardItemPojo>>(emptyList())
    val dataPlaces : StateFlow<List<CardItemPojo>> = _dataPlaces

    private val _dataFoodPlaces = MutableStateFlow<List<CardItemPojo>>(emptyList())
    val dataFoodPlaces : StateFlow<List<CardItemPojo>> = _dataFoodPlaces
    private val _dataHiddenGems = MutableStateFlow<List<CardItemPojo>>(emptyList())
    val dataHiddenGems : StateFlow<List<CardItemPojo>> = _dataHiddenGems

    fun loadPlaces(cityName: String) {
        viewModelScope.launch {
            _dataPlaces.value = appRepo.getPlaces(cityName)
        }
    }

    fun loadFoodPlaces(cityName: String) {
        viewModelScope.launch {
            _dataFoodPlaces.value = appRepo.getFoodPlaces(cityName)
        }
    }

    fun loadHiddenGems(cityName: String) {
        viewModelScope.launch {
            _dataHiddenGems.value = appRepo.getHiddenGems(cityName)
        }
    }


}