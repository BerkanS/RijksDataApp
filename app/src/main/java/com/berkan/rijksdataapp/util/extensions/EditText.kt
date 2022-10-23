package com.berkan.rijksdataapp.util.extensions

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.onSearch(onAction: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            onAction.invoke()
            return@setOnEditorActionListener true
        }
        false
    }
}