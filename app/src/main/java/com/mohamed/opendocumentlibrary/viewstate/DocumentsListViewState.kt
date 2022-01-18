package com.mohamed.opendocumentlibrary.viewstate

import com.mohamed.opendocumentlibrary.model.Document

sealed class DocumentsListViewState

class DocumentsDataListResult(val documents: List<Document>): DocumentsListViewState()

class ErrorState(val errorMessage: String): DocumentsListViewState()

object LoadingState: DocumentsListViewState()