package com.mohamed.opendocumentlibrary.parser

import com.mohamed.opendocumentlibrary.model.RequestDocumentResult

interface IParser {

	fun parseJsonStringToDocumentResultResponse(response: String): RequestDocumentResult
}