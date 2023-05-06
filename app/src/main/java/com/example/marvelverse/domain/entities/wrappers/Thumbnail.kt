package com.example.marvelverse.domain.entities.wrappers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(val path: String, val extension: String) : Parcelable
