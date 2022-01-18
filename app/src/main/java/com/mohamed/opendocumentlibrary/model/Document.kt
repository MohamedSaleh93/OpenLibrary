package com.mohamed.opendocumentlibrary.model

import android.os.Parcel
import android.os.Parcelable

data class Document(
	val title: String,
	val isbn: List<String>,
	val author: String
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString()!!,
		parcel.createStringArrayList()!!,
		parcel.readString()!!
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(title)
		parcel.writeStringList(isbn)
		parcel.writeString(author)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Document> {
		override fun createFromParcel(parcel: Parcel): Document {
			return Document(parcel)
		}

		override fun newArray(size: Int): Array<Document?> {
			return arrayOfNulls(size)
		}
	}
}
