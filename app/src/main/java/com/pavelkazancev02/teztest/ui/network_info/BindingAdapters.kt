package com.pavelkazancev02.teztest.ui.network_info

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = "https://s2.coinmarketcap.com/generated/sparklines/web/7d/usd/2011.png"
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}