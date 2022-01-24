package com.mohamed.opendocumentlibrary.datasource

import com.mohamed.opendocumentlibrary.model.CallResult
import com.mohamed.opendocumentlibrary.network.DocumentsApiService
import retrofit2.Response

class DocumentsListRemoteDataSourceImpl(
	private val documentsApiService: DocumentsApiService
	): IDocumentsListDataSource {

	override suspend fun requestDocuments(searchQuery: String): Response<CallResult> {
		return documentsApiService.getDocumentsList(searchQuery)
	}
}