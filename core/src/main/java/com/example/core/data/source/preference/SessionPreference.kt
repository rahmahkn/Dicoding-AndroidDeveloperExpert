package com.example.core.data.source.preference

import android.content.Context

class SessionPreference(context: Context) {
    companion object {
        private const val PREFS_NAME1 = "session_pref"
        private const val SESSION = "session"
    }

    private val preference1 = context.getSharedPreferences(PREFS_NAME1, Context.MODE_PRIVATE)

    fun setSession() {
        val editor = preference1.edit()
        editor.putString(SESSION, "LOGGED")
        editor.apply()
    }

}