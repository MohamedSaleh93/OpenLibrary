package com.mohamed.opendocumentlibrary.usecase

import com.mohamed.opendocumentlibrary.model.Document

interface IGetDocumentsUseCase {

	fun getDocumentsResultFromQuery(searchQuery: String): List<Document>
}