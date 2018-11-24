package com.example.deepak.albumlist

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.deepak.albumlist.application.AlbumApplication
import com.example.deepak.albumlist.model.AlbumData
import com.example.deepak.albumlist.room.AlbumDao
import com.example.deepak.albumlist.room.AppDataBase
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var albumDao: AlbumDao
    private lateinit var db: AppDataBase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext().applicationContext as AlbumApplication
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()
        albumDao = db.albumDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testDBInstance() {
        Assert.assertNotNull(db)
    }

    @Test
    @Throws(Exception::class)
    fun testDAOInstance() {
        Assert.assertNotNull(albumDao)
    }

    @Test
    @Throws(Exception::class)
    fun dbWriteTest() {
        val albumData = AlbumData(1, 1, "Title")
        db.albumDao().insert(albumData)
        val data = db.albumDao().getAll()
        data.subscribe { it ->
            assertEquals(true, !it.isEmpty())
        }
    }

    @Test
    @Throws(Exception::class)
    fun dbFetchTest() {
        val albumData = AlbumData(1, 1, "Title")
        db.albumDao().insert(albumData)
        val data = db.albumDao().getAll()
        data.subscribe { it ->
            val album = it[0]
            assertEquals("Title", album.title)
        }
    }
}