package com.example.deepak.albumlist.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.deepak.albumlist.model.AlbumData

@Database(entities = arrayOf(AlbumData::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}