package com.example.kimkazandi

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.kimkZazandi.roomDraw.DrawDao

import com.example.kimkazandi.roomDraw.DrawDatabase
import com.example.kimkazandi.model.Draw
import com.example.kimkazandi.model.Follow
import com.example.kimkazandi.roomFollow.FollowDao
import com.example.kimkazandi.roomFollow.FollowDatabase
import kotlinx.coroutines.launch

class DrawViewModel() : ViewModel() {
    private lateinit var dbDraw: DrawDatabase
    private lateinit var drawDao: DrawDao
    private lateinit var dbFollow: FollowDatabase
    private lateinit var followDao: FollowDao

    private val draw = MutableLiveData<MutableList<Draw>>(mutableListOf())
    val data: LiveData<MutableList<Draw>> = draw

    private val follow = MutableLiveData<MutableList<Follow>>(mutableListOf())
    val dataFolow: LiveData<MutableList<Follow>> = follow




    fun getAllData(context:Context,baseUrl:String){

        dbDraw = Room.databaseBuilder(context, DrawDatabase::class.java, "draws")
            .allowMainThreadQueries()
            .build()

        drawDao = dbDraw.drawDao()

        viewModelScope.launch{

          draw.value=  drawDao.getAllByBaseUrl(baseUrl)
        }


    }
    fun getFollowed(context:Context){
        dbFollow = Room.databaseBuilder(context, FollowDatabase::class.java, "follow")
            .allowMainThreadQueries()
            .build()
        followDao=dbFollow.followDao()

        viewModelScope.launch{

            follow.value=  followDao.getAll()
        }


    }

}