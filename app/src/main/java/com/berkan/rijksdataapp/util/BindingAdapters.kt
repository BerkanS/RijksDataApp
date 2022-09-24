package com.berkan.rijksdataapp.util

import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import coil.load
import com.berkan.rijksdataapp.R
import java.lang.Exception

@BindingAdapter("setImageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val imageToLoad = url ?: R.drawable.rijksmuseum_logo_white

    imageView.load(imageToLoad) {
        placeholder(R.drawable.rijksmuseum_logo_white)
        crossfade(250)
    }
}

@BindingAdapter("setImageUrlNoPlaceholder")
fun setImageUrlNoPlaceholder(imageView: ImageView, url: String?) {
    val imageToLoad = url ?: R.drawable.rijksmuseum_logo_white

    try {
        imageView.load(imageToLoad)
    } catch (e: Exception) {
        Toast.makeText(imageView.context, "Error while loading image, possibly too big", Toast.LENGTH_SHORT)
            .show()
    }
}