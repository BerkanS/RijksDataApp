package com.berkan.rijksdataapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.berkan.rijksdataapp.R

@BindingAdapter("setImageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val imageToLoad = url ?: R.drawable.rijksmuseum_logo_white

    imageView.load(imageToLoad) {
        placeholder(R.drawable.rijksmuseum_logo_white)
        crossfade(250)
    }
}