package com.mehdi.bbcnews.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsSourceUi(
    val id: String,
    val name: String,
) : Parcelable
