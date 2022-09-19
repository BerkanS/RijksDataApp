package com.berkan.rijksdataapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "art_object")
data class ArtObject(
    @PrimaryKey
    val objectNumber: String,
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