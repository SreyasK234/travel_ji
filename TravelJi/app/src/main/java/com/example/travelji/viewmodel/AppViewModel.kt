package com.example.travelji.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelji.db.RemoteDBHelper
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

//    val selectedPlaces = MutableStateFlow<List<CardItemPojo>>()
    fun loadPlaces() {
        viewModelScope.launch {
            _dataPlaces.value = appRepo.getPlaces()
        }
    }

    fun loadFoodPlaces(){
        viewModelScope.launch {
            _dataFoodPlaces.value = RemoteDBHelper.getCityCategory("Hyderabad", "recommendedRestaurants")
        }
    }


}