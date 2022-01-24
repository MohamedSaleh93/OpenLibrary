package com.mohamed.opendocumentlibrary

import android.app.Application
import com.mohamed.opendocumentlibrary.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OpenDocumentLibraryApp: Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger()
			androidContext(this@OpenDocumentLibraryApp)
			modules(listOf(
				dataModule,
				networkModule,
				repositoryModule,
				parserModule,
				useCaseModule,
				viewModelModule
			))
		}
	}
}