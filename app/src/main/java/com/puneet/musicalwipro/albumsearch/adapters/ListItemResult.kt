package com.puneet.musicalwipro.albumsearch.adapters

import com.puneet.musicalwipro.albumsearch.model.CommonSearchResult

open class ListItemResult(
    val searchResult: CommonSearchResult
) : ListItem {

    override fun getListItemType(): ListItem.Type {
        return ListItem.Type.RESULT
    }

}