package com.example.travelji.view.composables.mytrip_page

data class TripItem(
    val id: Int,
    val name: String,
    val description: String,
    val rating: Double,
    val category: String, // "Place", "Restaurant", "Hidden Gem"
    val imageResId: Int? = null
)