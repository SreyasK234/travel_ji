package com.example.travelji.db

import android.util.Log
import androidx.compose.runtime.snapshots.Snapshot
import com.example.travelji.model.CardItemPojo
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import kotlinx.coroutines.tasks.await


object RemoteDBHelper{

    suspend fun getCityCategory(
        city: String,
        category: String
    ) : List<CardItemPojo>{

        return try {
            val ref = FirebaseDatabase
                .getInstance()
                .getReference("Cities")
                .child(city)
                .child(category)
            val snapshot = ref.get().await()

            val list = mutableListOf<CardItemPojo>()

            for(child in snapshot.children){
                val item = child.getValue(CardItemPojo::class.java)
                item?.let { list.add(it) }
            }
            list
        }catch (e : Exception){
            Log.d("Exception", e.message.toString())
            emptyList()
        }

    }



}