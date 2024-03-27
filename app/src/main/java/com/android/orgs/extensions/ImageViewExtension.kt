package com.android.orgs.extensions

import android.os.Build
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.android.orgs.R

fun ImageView.tentaCarregarImagem(url: String? = null, imageLoader: ImageLoader? = null) {
    val loader = imageLoader ?: ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory(true))
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    load(url, loader) {
        fallback(R.drawable.imagem_padrao)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}

