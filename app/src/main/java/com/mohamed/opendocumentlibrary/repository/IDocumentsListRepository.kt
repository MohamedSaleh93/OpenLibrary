package com.mohamed.opendocumentlibrary.repository

import com.mohamed.opendocumentlibrary.model.CallResult
import retrofit2.Response

interface IDocumentsListRepository {

	suspend fun requestDocuments(searchQuery: String): Response<CallResult>
}