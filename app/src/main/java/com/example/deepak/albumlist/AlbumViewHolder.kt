package com.example.deepak.albumlist

import android.view.View
import com.example.deepak.albumlist.base.BaseViewHolder
import com.example.deepak.albumlist.base.OnClick
import com.example.deepak.albumlist.model.AlbumData
import kotlinx.android.synthetic.main.layout_album.view.*

class AlbumViewHolder<T>(val view: View) : BaseViewHolder<AlbumData>(view) {
    override fun onBindData(dataModel: AlbumData, onClickListener: OnClick<AlbumData>) {
        itemView.txtAlbumName.text = dataModel.title
        itemView.setOnClickListener { onClickListener.onClick(dataModel) }
    }
}