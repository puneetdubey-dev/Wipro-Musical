package com.puneet.musicalwipro.albumsearch.adapters

interface ListItem {
    enum class Type {
        RESULT
    }
    fun getListItemType(): Type
}