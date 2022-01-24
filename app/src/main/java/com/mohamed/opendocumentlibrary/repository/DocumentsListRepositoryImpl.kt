package com.mohamed.opendocumentlibrary.repository

import com.mohamed.opendocumentlibrary.datasource.IDocumentsListDataSource
import com.mohamed.opendocumentlibrary.model.CallResult
import retrofit2.Response

class DocumentsListRepositoryImpl(
	private val documentsRemoteDataSource: IDocumentsListDataSource
	): IDocumentsListRepository {

	override suspend fun requestDocuments(searchQuery: String): Response<CallResult> {
		return documentsRemoteDataSource.requestDocuments(searchQuery)
	}
}