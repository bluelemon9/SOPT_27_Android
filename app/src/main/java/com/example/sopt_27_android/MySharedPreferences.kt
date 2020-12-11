package com.example.sopt_27_android

import android.content.Context
import android.content.SharedPreferences

object MySharedPreferences {
    private val USER : String = "user"

    fun setId(context: Context, input: String) {
        val prefs : SharedPreferences = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putString("USER_ID", input)
        editor.apply()
    }

    fun getId(context: Context) : String {
        val prefs : SharedPreferences = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return prefs.getString("USER_ID", "").toString()
    }

    fun setPw(context: Context, input: String) {
        val prefs : SharedPreferences = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putString("USER_PW", input)
        editor.apply()
    }

    fun getPw(context: Context) : String {
        val prefs : SharedPreferences = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return prefs.getString("USER_PW", "").toString()
    }

    fun clearUser(context: Context) {
        val prefs : SharedPreferences = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

}