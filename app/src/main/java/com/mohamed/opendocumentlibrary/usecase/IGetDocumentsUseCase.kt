package com.mohamed.opendocumentlibrary.usecase

import com.mohamed.opendocumentlibrary.model.RequestDocumentResult

interface IGetDocumentsUseCase {

	suspend fun getDocumentsResultFromQuery(searchQuery: String): RequestDocumentResult
}