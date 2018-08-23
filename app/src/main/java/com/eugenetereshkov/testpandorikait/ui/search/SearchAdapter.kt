package com.eugenetereshkov.testpandorikait.ui.search

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.eugenetereshkov.testpandorikait.R
import com.eugenetereshkov.testpandorikait.entity.Result
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_result.*


class SearchAdapter : ListAdapter<Result, SearchAdapter.ResultViewHolder>(diff) {

    private object diff : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(p0: Result, p1: Result): Boolean = p0 === p1
        override fun areContentsTheSame(p0: Result, p1: Result): Boolean = p0 == p1
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ResultViewHolder =
            ResultViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_result, p0, false))

    override fun onBindViewHolder(p0: ResultViewHolder, p1: Int) {
        p0.bind(getItem(p1))
    }

    class ResultViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Result) {
            textViewName.text = item.artistName
            textViewAlbum.text = item.collectionName

            Glide.with(imageView)
                    .load(item.artworkUrl60)
                    .into(imageView)
        }
    }
}
