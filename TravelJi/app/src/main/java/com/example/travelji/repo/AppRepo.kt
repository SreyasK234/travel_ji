package com.example.travelji.repo

import com.example.travelji.db.RemoteDBHelper
import com.example.travelji.model.CardItemPojo

class AppRepo {

    suspend fun getPlaces(cityName: String): List<CardItemPojo>{
        val list = RemoteDBHelper.getCityCategory(cityName, "recommendedPlaces")
        return list
    }
    suspend fun getFoodPlaces(cityName: String): List<CardItemPojo>{
        val list = RemoteDBHelper.getCityCategory(cityName, "recommendedRestaurants")
        return list
    }
    suspend fun getHiddenGems(cityName: String): List<CardItemPojo>{
        val list = RemoteDBHelper.getCityCategory(cityName, "hiddenGems")
        return list
    }



}