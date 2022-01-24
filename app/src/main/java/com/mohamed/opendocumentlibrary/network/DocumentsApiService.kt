package com.mohamed.opendocumentlibrary.network

import com.mohamed.opendocumentlibrary.model.CallResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DocumentsApiService {

	@GET("/search.json")
	fun getDocumentsList(@Query(value="q", encoded = true) query: String): Call<CallResult>
}