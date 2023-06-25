package com.example.kimkazandi.roomFollow

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.kimkazandi.model.Follow

@Database(entities = [Follow::class], version = 1)
abstract class FollowDatabase : RoomDatabase() {
    abstract fun followDao(): FollowDao
}