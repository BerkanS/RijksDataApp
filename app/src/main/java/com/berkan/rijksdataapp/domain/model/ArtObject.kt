package com.berkan.rijksdataapp.domain.model

import com.squareup.moshi.Json

data class ArtObject(
    val title: String,
    @Json(name = "principalOrFirstMaker")
    val author: String,
    val longTitle: String?,
    val webImage: ObjectImage?,
    val headerImage: ObjectImage?
)

data class ObjectImage(
    val url: String?
)