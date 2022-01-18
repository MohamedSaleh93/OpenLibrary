package com.mohamed.opendocumentlibrary.model

import java.lang.Exception

data class RequestDocumentResult(val exception: Exception? = null, val documentsList: List<Document>? = null)