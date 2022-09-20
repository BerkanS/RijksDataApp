package com.berkan.rijksdataapp.domain.model

sealed class FavoriteObject(
    open var type: Type
)

enum class Type {
    HEADER,
    ART_OBJECT;

    companion object {
        fun first(ordinalNumber: Int) = values().find { it.ordinal == ordinalNumber }
    }
}
