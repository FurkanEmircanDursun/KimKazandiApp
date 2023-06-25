package com.example.kimkazandi.roomDraw

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kimkZazandi.roomDraw.DrawDao
import com.example.kimkazandi.model.Draw

@Database(entities = [Draw::class], version = 1)
abstract class DrawDatabase : RoomDatabase() {
    abstract fun drawDao(): DrawDao
}