package com.mohamed.opendocumentlibrary.di

import com.mohamed.opendocumentlibrary.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
	viewModel {
		MainViewModel(get())
	}
}