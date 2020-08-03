package com.puneet.musicalwipro.albumsearch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puneet.musicalwipro.albumsearch.extensions.toCommonSearchResult
import com.puneet.musicalwipro.albumsearch.repository.AlbumSearch
import com.puneet.musicalwipro.albumsearch.model.CommonSearchResult
import com.puneet.musicalwipro.albumsearch.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(val albumSearch: AlbumSearch) : ViewModel() {

    val searchResultsLive: SingleLiveEvent<List<CommonSearchResult>> by lazy {
        SingleLiveEvent<List<CommonSearchResult>>()
    }

    val searchResultsCombined = mutableListOf<CommonSearchResult>()

    fun search(searchText: String) {

        if (searchText.isBlank()) return

        viewModelScope.launch(Dispatchers.IO) {

            searchResultsCombined.clear()
            val albumSearchResult = albumSearch.searchAlbum(searchText)
            albumSearchResult?.toCommonSearchResult()?.let { searchResultsCombined.addAll(it) }

            searchResultsLive.postValue(searchResultsCombined)
        }
    }
}
