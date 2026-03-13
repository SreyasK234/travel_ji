package com.example.travelji.repo

import com.example.travelji.db.RemoteDBHelper
import com.example.travelji.model.CardItemPojo

class AppRepo {

    suspend fun getPlaces() : List<CardItemPojo>{
        val list = RemoteDBHelper.getCityCategory("Hyderabad", "recommendedPlaces")
        return list
    }
    suspend fun getFoodPlaces() : List<CardItemPojo>{
        val list = RemoteDBHelper.getCityCategory("Hyderabad", "recommendedRestaurants")
        return list
    }
    suspend fun getHiddenGems() : List<CardItemPojo>{
        val list = RemoteDBHelper.getCityCategory("Hyderabad", "hiddenGems")
        return list
    }



}