package com.example.kimkZazandi.roomDraw

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kimkazandi.model.Draw


@Dao
interface DrawDao {

    @Query("Select * From draws")
    fun getAll(): MutableList<Draw>


    @Query("SELECT * FROM draws WHERE baseUrl = :baseUrl")
    fun getAllByBaseUrl(baseUrl: String): MutableList<Draw>


    @Insert
    fun insert(draw: Draw)


    @Delete
    fun delete(draw: Draw)



    @Query("DELETE FROM draws")
    fun deleteAll()



}