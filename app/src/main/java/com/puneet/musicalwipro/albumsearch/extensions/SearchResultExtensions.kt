package com.puneet.musicalwipro.albumsearch.extensions

import com.puneet.musicalwipro.albumsearch.adapters.ListItem
import com.puneet.musicalwipro.albumsearch.adapters.ListItemResult
import com.puneet.musicalwipro.albumsearch.model.Album
import com.puneet.musicalwipro.albumsearch.model.AlbumSearchResult
import com.puneet.musicalwipro.albumsearch.model.CommonSearchResult
import com.puneet.musicalwipro.albumsearch.model.ImageSize

fun AlbumSearchResult?.toCommonSearchResult(): List<CommonSearchResult>? {
    return this?.results?.albumList?.albums?.map { it.toCommonSearchResult() }
}

fun List<CommonSearchResult>.toListItem(): List<ListItem> {
    return this.map { it.toListItem() }
}

private fun CommonSearchResult.toListItem(): ListItem {
    return ListItemResult(this)
}

private fun Album.toCommonSearchResult(): CommonSearchResult {

    return CommonSearchResult(
        SearchResultType.Album,
        this.mbid,
        this.name,
        "Artist: ${this.artist}",
        this.url,
        this.images?.firstOrNull { it.imageSize == ImageSize.Medium }?.url,
        this.images?.firstOrNull { it.imageSize == ImageSize.Large }?.url
    )
}

enum class SearchResultType {
    Album
}