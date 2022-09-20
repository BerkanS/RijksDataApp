package com.berkan.rijksdataapp.domain.model

data class AuthorHeader(
    val authorName: String
) : FavoriteObject(Type.HEADER)