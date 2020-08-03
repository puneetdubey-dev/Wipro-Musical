package com.puneet.musicalwipro.albumsearch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puneet.musicalwipro.albumsearch.databinding.RowSearchResultBinding
import com.puneet.musicalwipro.albumsearch.model.CommonSearchResult

class SearchResultAdapter(
    private var items: List<ListItem>,
    private val listener: ListClickListener<CommonSearchResult>
) : RecyclerView.Adapter<AlbumViewHolder>() {

    fun updateData(newItems: List<ListItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return ListItem.Type.RESULT.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder{
        return  AlbumViewHolder(
            RowSearchResultBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val albumItem = (items[position] as ListItemResult).searchResult
        holder.apply {
            bind(albumItem, listener)
            itemView.setOnClickListener { listener.onRowTap(albumItem) }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}

/**
 * ViewHolder that is bind to FactsDetailAdapter
 * Updates individual item UI
 */
class AlbumViewHolder(private val binding: RowSearchResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: CommonSearchResult,
        listener: ListClickListener<CommonSearchResult>
    ) {
        binding.apply {
            album  = item
            executePendingBindings()
        }

    }
//    fun onClickAlbumItem() {
////            listener.onRowTap(data)
//        }
}
