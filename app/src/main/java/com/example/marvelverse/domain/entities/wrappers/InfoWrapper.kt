package com.example.marvelverse.domain.entities.wrappers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoWrapper(val collectionURI: String, val available: Int) : Parcelable
