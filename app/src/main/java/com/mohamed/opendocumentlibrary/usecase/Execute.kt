package com.mohamed.opendocumentlibrary.usecase

import com.mohamed.opendocumentlibrary.model.Document

interface Execute {
	fun onSuccess(result: List<Document>)

	fun onError(ex: Exception)
}