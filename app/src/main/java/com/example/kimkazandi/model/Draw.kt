package com.example.kimkazandi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "draws")

data class Draw(

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

) :Serializable{
    @PrimaryKey(autoGenerate = true)
    var id = 0

}






