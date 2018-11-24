package com.example.deepak.albumlist.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.deepak.albumlist.model.AlbumData
import io.reactivex.Maybe

@Dao
interface AlbumDao {
    @Query("select * from albumdata")
    fun getAll(): Maybe<List<AlbumData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg albumData: AlbumData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albumData: List<AlbumData>)
}