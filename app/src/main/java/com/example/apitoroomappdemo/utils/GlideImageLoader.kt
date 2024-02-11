package com.example.apitoroomappdemo.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.apitoroomappdemo.R

/**
 * Created by Tirth Patel.
 */
fun glideImageLoader(
    context: Context,
    imageUrl: String?,
    targetedImageView: ImageView,
    roundCorner: Int
) {
    val requestOptions = RequestOptions()
        .transform(RoundedCorners(roundCorner))

    Glide.with(context)
        .load(imageUrl ?: R.drawable.news_default)
        .apply(requestOptions)
        .into(targetedImageView)
}