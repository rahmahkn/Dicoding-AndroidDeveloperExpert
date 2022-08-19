package com.example.core.data.source.remote

import org.json.JSONObject
import retrofit2.Response

fun getErrorMessageFromApi(response: Response<*>, targetString: String): String {
    val jsonObject = JSONObject(response.errorBody()?.charStream()?.readText().orEmpty())

    return jsonObject.getString(targetString).orEmpty()
}