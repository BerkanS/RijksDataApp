package com.berkan.rijksdataapp.util

import androidx.room.TypeConverter
import com.berkan.rijksdataapp.domain.model.ObjectImage

class ObjectImageTypeConverter {

    @TypeConverter
    fun from(objectImage: ObjectImage): String? {
        return objectImage.url
    }

    @TypeConverter
    fun to(string: String?): ObjectImage {
        return ObjectImage(string)
    }
}