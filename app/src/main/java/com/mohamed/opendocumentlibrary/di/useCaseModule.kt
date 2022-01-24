package com.mohamed.opendocumentlibrary.di

import com.mohamed.opendocumentlibrary.usecase.GetDocumentsUseCase
import com.mohamed.opendocumentlibrary.usecase.IGetDocumentsUseCase
import org.koin.dsl.module

val useCaseModule = module {
	single<IGetDocumentsUseCase> { GetDocumentsUseCase(get()) }
}