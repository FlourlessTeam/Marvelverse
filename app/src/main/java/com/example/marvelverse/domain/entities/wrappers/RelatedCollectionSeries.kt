package com.example.marvelverse.domain.entities.wrappers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RelatedCollectionSeries(val collectionURI: String="", val available: Int=0) : Parcelable