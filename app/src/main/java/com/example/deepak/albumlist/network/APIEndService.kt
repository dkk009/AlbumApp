package com.example.deepak.albumlist.network

import com.example.deepak.albumlist.model.AlbumData
import io.reactivex.Single
import retrofit2.http.GET

interface APIEndService {
    @GET("albums")
    fun fetchAlbumList(): Single<List<AlbumData>>
}