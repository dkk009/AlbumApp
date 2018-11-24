package com.example.deepak.albumlist

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deepak.albumlist.network.APIEndService
import com.example.deepak.albumlist.base.AlbumSyncError
import com.example.deepak.albumlist.base.BaseFragment
import com.example.deepak.albumlist.base.OnClick
import com.example.deepak.albumlist.model.AlbumData
import com.example.deepak.albumlist.room.AppDataBase
import com.example.deepak.albumlist.utils.Utils
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), AlbumContract.View {
    override fun updateAlbum(albumData: List<AlbumData>) {
        albumList.addAll(albumData)
        albumAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        progressView.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressView.visibility = View.GONE
    }

    override fun showError(error: AlbumSyncError) {
        Utils.showError(activity!!, getString(R.string.server_error))
    }

    @Inject
    lateinit var apiEndService: APIEndService
    private var presenter: AlbumPresenter? = null
    @Inject
    lateinit var database: AppDataBase
    private val albumList = ArrayList<AlbumData>()
    private val albumAdapter = AlbumAdapter(albumList, object : OnClick<AlbumData> {
        override fun onClick(dataModel: AlbumData) {
            //Have to take action
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = AlbumPresenter(this, database, apiEndService)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter?.fetchAlbumList()
        rvAlbum.layoutManager = GridLayoutManager(activity, 2)
        rvAlbum.adapter = albumAdapter
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}