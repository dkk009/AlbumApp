package com.example.deepak.albumlist.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class AlbumData(
    @SerializedName("userId")
    @ColumnInfo(name = "userId") val userId: Int,

    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,

    @SerializedName("title")
    @ColumnInfo(name = "title") val title: String?
)