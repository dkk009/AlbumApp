package com.example.deepak.albumlist.base

interface APIListener<T> : DataFetchListener<T> {
    fun onError(error: AlbumSyncError)
}

interface DataFetchListener<T> {
    fun onSuccess(dataModel: T)
}

interface OnClick<T> {
    fun onClick(dataModel: T)
}