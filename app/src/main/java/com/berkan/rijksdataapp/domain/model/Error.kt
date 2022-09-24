package com.berkan.rijksdataapp.domain.model

const val CODE_UNAUTHORIZED = 401

sealed class LocalError(
    override val message: String
) : Exception() {
    object EmptySearch: LocalError(message = "Nothing found, try another search query")
    object BadApiKey: LocalError(message = "Invalid API key")
}