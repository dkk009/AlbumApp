package com.example.deepak.albumlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.deepak.albumlist.base.OnClick
import com.example.deepak.albumlist.model.AlbumData

class AlbumAdapter : RecyclerView.Adapter<AlbumViewHolder<AlbumData>> {
    private var albumList: List<AlbumData>
    private var onClick: OnClick<AlbumData>

    constructor(albumList: List<AlbumData>, onClick: OnClick<AlbumData>) : super() {
        this.albumList = albumList
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): AlbumViewHolder<AlbumData> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_album,
            parent, false)
        return AlbumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(viewHolder: AlbumViewHolder<AlbumData>, pos: Int) {
        viewHolder.onBindData(albumList[pos], onClick)
    }

}