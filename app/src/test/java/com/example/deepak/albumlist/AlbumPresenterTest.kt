package com.example.deepak.albumlist

import com.example.deepak.albumlist.network.APIEndService
import com.example.deepak.albumlist.model.AlbumData
import com.example.deepak.albumlist.room.AppDataBase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit


@RunWith(JUnit4::class)
class AlbumPresenterTest {

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()
    @Mock
    lateinit var dataBase: AppDataBase
    @Mock
    lateinit var apiEndService: APIEndService

    @Mock
    lateinit var view: AlbumContract.View


    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testSortData() {
        val presenter= AlbumPresenter(view,dataBase,apiEndService)
        val testData = getTestData()
        val sortData = presenter.sortAlbumList(testData)
        Assert.assertEquals("a",sortData[0].title)
    }

    @Test
    fun testSortWithSameLetterData() {
        val presenter= AlbumPresenter(view,dataBase,apiEndService)
        val testData = getTestSameLetterData()
        val sortData = presenter.sortAlbumList(testData)
        Assert.assertEquals("a",sortData[0].title)
    }

    private fun getTestSameLetterData(): List<AlbumData> {
        val albumDataList = ArrayList<AlbumData>()
        albumDataList.add(AlbumData(1, 1, "aa"))
        albumDataList.add(AlbumData(1, 1, "a"))
        albumDataList.add(AlbumData(1, 1, "pa"))
        albumDataList.add(AlbumData(1, 1, "aaa"))
        albumDataList.add(AlbumData(1, 1, "aa"))
        return albumDataList
    }

    private fun getTestData(): List<AlbumData> {
        val albumDataList = ArrayList<AlbumData>()
        albumDataList.add(AlbumData(1, 1, "z"))
        albumDataList.add(AlbumData(1, 1, "s"))
        albumDataList.add(AlbumData(1, 1, "p"))
        albumDataList.add(AlbumData(1, 1, "a"))
        albumDataList.add(AlbumData(1, 1, "b"))
        return albumDataList
    }
}