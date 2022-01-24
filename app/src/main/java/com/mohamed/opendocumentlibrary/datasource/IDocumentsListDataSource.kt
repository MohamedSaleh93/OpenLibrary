package com.mohamed.opendocumentlibrary.datasource

import com.mohamed.opendocumentlibrary.model.CallResult
import retrofit2.Response

interface IDocumentsListDataSource {

	suspend fun requestDocuments(searchQuery: String): Response<CallResult>
}