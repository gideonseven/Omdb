package com.don.omdb.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.don.omdb.R

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
object GlideUtil {
    fun glideOverrideSize(context: Context, url: String, imageView: ImageView) {

        val requestOptions = RequestOptions()
            .placeholder(R.mipmap.ic_launcher).override(200, 250)
            .transform(CenterCrop(), RoundedCorners(8))

        try {
            Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView)
        } catch (e: Exception) {
            Glide.with(context)
                .load(R.mipmap.ic_launcher)
                .into(imageView)
        }

    }
}