package com.app.comicapp.managers

import android.content.Context
import android.content.SharedPreferences

class UserSessionManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)

    fun saveAuthToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("auth_token", token)
        editor.apply()
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }

    fun logout() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
