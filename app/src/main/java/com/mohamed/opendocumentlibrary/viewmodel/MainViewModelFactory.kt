package com.mohamed.opendocumentlibrary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohamed.opendocumentlibrary.usecase.IGetDocumentsUseCase

class MainViewModelFactory(
	private val getDocumentsUseCase:IGetDocumentsUseCase
): ViewModelProvider.Factory {
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return MainViewModel(getDocumentsUseCase) as T
	}
}