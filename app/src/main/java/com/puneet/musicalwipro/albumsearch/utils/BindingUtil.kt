package com.puneet.musicalwipro.albumsearch.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.puneet.musicalwipro.albumsearch.R

object BindingUtil {
    @JvmStatic
    @BindingAdapter("setAlbumImage")
    fun setAlbumImage(view: ImageView, url: String) {
        try {
            if(url.isNotEmpty()) {
                Glide.with(view.context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(
                    R.drawable.ic_music).into(view)
            }else{
                Glide.with(view.context).load(R.drawable.ic_photo).centerInside().into(view)
            }
        } catch (e: Exception) {
            Glide.with(view.context).load(R.drawable.ic_photo).centerCrop().into(view)
        }
    }
}