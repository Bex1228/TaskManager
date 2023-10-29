package com.example.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    fun isShow(): Boolean {
        return pref.getBoolean(SHOWED_KEY, false)
    }

    fun onShowed() {
        pref.edit().putBoolean(SHOWED_KEY, true).apply()
    }

    fun getName(): String? {
        return pref.getString(SAVE_NAME, "")

    }

    fun saveName(name: String) {
        pref.edit().putString(SAVE_NAME, name).apply()
    }

    fun setImage(imageUri: String) {
        pref.edit().putString(SAVE_IMAGE, imageUri).apply()
    }

    fun getImage(): String {
        return pref.getString(SAVE_IMAGE, "").toString()
    }

    companion object {
        const val SAVE_IMAGE = "save.image"
        const val SAVE_NAME = "save.name"
        const val PREF_NAME = "pref.name"
        const val SHOWED_KEY = "showed.key"
    }
}