package com.mohamed.opendocumentlibrary.datasource

import com.mohamed.opendocumentlibrary.model.RequestDocumentResult
import com.mohamed.opendocumentlibrary.network.DocumentsApiService

class DocumentsListRemoteDataSourceImpl(
	private val documentsApiService: DocumentsApiService
	): IDocumentsListDataSource {

	override fun requestDocuments(searchQuery: String): RequestDocumentResult {
		return try {
			val call = documentsApiService.getDocumentsList(searchQuery).execute()
			if (call.isSuccessful) {
				RequestDocumentResult(documentsList = call.body()?.docs)
			} else {
				RequestDocumentResult(exception = Exception(call.errorBody()?.string()))
			}
		} catch (e: java.lang.Exception) {
			RequestDocumentResult(exception = e)
		}
	}
}