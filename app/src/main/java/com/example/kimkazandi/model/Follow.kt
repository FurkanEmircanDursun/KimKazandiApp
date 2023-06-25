package com.example.kimkazandi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "follow")
data class Follow(

    @ColumnInfo(name = "baseUrl")
    var baseUrl: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "time")
    var time: String,
    @ColumnInfo(name = "count")
    var count: String,
    @ColumnInfo(name = "price")
    var price: String,
    @ColumnInfo(name = "img")
    var img: String,
    @ColumnInfo(name = "link")
    var link: String,

) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}


