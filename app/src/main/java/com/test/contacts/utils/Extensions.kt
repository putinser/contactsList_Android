package com.test.contacts.utils

import android.net.Uri
import android.widget.ImageView
import com.test.contacts.R
import com.bumptech.glide.Glide

fun Uri?.showInImageView(iv: ImageView) {
    if (this == null || this.toString().isEmpty()) {
        Glide.with(iv.context).load(R.drawable.ic_placeholder_no_user).into(iv)
    } else {
        Glide.with(iv.context).load(this).into(iv)
    }
}