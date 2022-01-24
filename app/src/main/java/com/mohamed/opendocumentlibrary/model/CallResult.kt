package com.mohamed.opendocumentlibrary.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CallResult(
	@SerialName("docs")
	val docs: List<Document>)