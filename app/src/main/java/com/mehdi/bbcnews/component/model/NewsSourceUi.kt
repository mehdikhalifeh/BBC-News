package com.mehdi.bbcnews.component.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsSourceUi(
    val id: String,
    val name: String,
) : Parcelable
