package com.mohamed.opendocumentlibrary.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Document(
	@SerialName("title")
	val title: String,
	@SerialName("isbn")
	val isbn: List<String>? = null,
	@SerialName("author_name")
	val author: List<String>? = null
): Parcelable