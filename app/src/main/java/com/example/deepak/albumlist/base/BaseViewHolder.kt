package com.example.deepak.albumlist.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected val context: Context

    init {
        context = itemView.context
    }

    abstract fun onBindData(dataModel: T, onClickListener: OnClick<T>)
}