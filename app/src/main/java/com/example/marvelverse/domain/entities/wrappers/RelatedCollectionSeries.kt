package com.example.marvelverse.domain.entities.wrappers

import java.io.Serializable


data class RelatedCollectionSeries(val collectionURI: String="", val available: Int=0) : Serializable