package com.example.kimkazandi.roomFollow

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kimkazandi.model.Draw
import com.example.kimkazandi.model.Follow

@Dao
interface FollowDao {
    @Query("Select * From follow")
    fun getAll(): MutableList<Follow>



    @Query("SELECT * FROM follow WHERE baseUrl = :baseUrl")
    fun getAllByBaseUrl(baseUrl: String): MutableList<Follow>

    @Query("SELECT * FROM follow WHERE link = :link")
    fun getAllFollowByLink(link: String): MutableList<Follow>

    @Insert
    fun insert(follow: Follow)


    @Query("DELETE  FROM follow  WHERE link = :link")
    fun delete(link: String)





}