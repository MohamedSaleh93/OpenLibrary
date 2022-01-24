package com.mohamed.opendocumentlibrary.usecase

import com.mohamed.opendocumentlibrary.model.RequestDocumentResult
import com.mohamed.opendocumentlibrary.repository.IDocumentsListRepository

class GetDocumentsUseCase(
	private val documentsListRepository: IDocumentsListRepository
) : IGetDocumentsUseCase {

	override suspend fun getDocumentsResultFromQuery(
		searchQuery: String
	): RequestDocumentResult {
		return try {
			val call = documentsListRepository.requestDocuments(searchQuery.replace(" ", "+"))
			if (call.isSuccessful) {
				RequestDocumentResult(documentsList = call.body()?.docs)
			} else {
				RequestDocumentResult(exception = Exception(call.errorBody()?.toString()))
			}
		} catch (e: java.lang.Exception) {
			RequestDocumentResult(exception = e)
		}
	}
}