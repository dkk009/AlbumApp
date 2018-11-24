package com.example.deepak.albumlist

import com.example.deepak.albumlist.base.*
import com.example.deepak.albumlist.constants.AppConstants
import com.example.deepak.albumlist.model.AlbumData
import com.example.deepak.albumlist.network.APIEndService
import com.example.deepak.albumlist.room.AppDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface AlbumContract {
    interface View : BaseContract.View {
        fun updateAlbum(albumData: List<AlbumData>)
    }

    interface Presenter : BaseContract.Presenter {
        fun fetchAlbumList()
        fun sortAlbumList(albumData: List<AlbumData>): List<AlbumData>
    }

    interface Interactor : BaseContract.Interactor {
        fun fetchAlbumList(listener: APIListener<List<AlbumData>>)
        fun fetchAlbumFromDatabase(listener: DataFetchListener<List<AlbumData>>)
    }
}

class AlbumPresenter(val view: AlbumContract.View, roomDatabase: AppDataBase, apiService: APIEndService) :
    AlbumContract.Presenter {
    /**
     * sortedAlbumList method - will sort album list based on album title
     */
    override fun sortAlbumList(albumData: List<AlbumData>): List<AlbumData> {
        return albumData.sortedBy { it -> it.title }

    }

    private val albumInteractor = AlbumInteractor(roomDatabase, apiService)
    /**
     * fetchAlbumList method, will fetch album details from remote server and store
     * into local storage and give final sorted list into view
     */
    override fun fetchAlbumList() {
        view.showProgress()
        albumInteractor.fetchAlbumFromDatabase(object : DataFetchListener<List<AlbumData>> {
            override fun onSuccess(dataModel: List<AlbumData>) {
                if (!dataModel.isEmpty()) {
                    view.hideProgress()
                    view.updateAlbum(sortAlbumList(dataModel))
                }
            }
        })
        albumInteractor.fetchAlbumList(object : APIListener<List<AlbumData>> {
            override fun onSuccess(dataModel: List<AlbumData>) {
                view.updateAlbum(sortAlbumList(dataModel))
            }

            override fun onError(error: AlbumSyncError) {
                view.hideProgress()
                view.showError(error)
            }
        })
    }

    override fun onDestroy() {
        albumInteractor.clear()
    }
}


class AlbumInteractor(val dataBase: AppDataBase, apiService: APIEndService) : BaseInteractor(apiService),
    AlbumContract.Interactor {
    /**
     * fetchAlbumFromDatabase method will fetch album data from local storage
     */
    override fun fetchAlbumFromDatabase(listener: DataFetchListener<List<AlbumData>>) {
        val albumRequest = dataBase.albumDao().getAll().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
        albumRequest.doOnSubscribe { compositeDisposable.add(it) }
            .subscribe { listener.onSuccess(it) }
    }

    /**
     * fetchAlbumList method will fetch album data from server, and store into local storage
     */
    override fun fetchAlbumList(listener: APIListener<List<AlbumData>>) {
        compositeDisposable.add(
            apiService.fetchAlbumList()
                .doOnSuccess(dataBase.albumDao()::insertAll)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listener.onSuccess(it) },
                    { listener.onError(AlbumSyncError(it, "", AppConstants.SERVER_ERROR)) })
        )

    }

    override fun clear() {
        compositeDisposable.clear()
    }

}