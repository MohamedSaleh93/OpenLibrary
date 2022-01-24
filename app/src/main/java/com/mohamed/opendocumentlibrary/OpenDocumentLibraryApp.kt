package com.mohamed.opendocumentlibrary

import android.app.Application
import com.mohamed.opendocumentlibrary.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class OpenDocumentLibraryApp: Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
			androidContext(this@OpenDocumentLibraryApp)
			modules(listOf(
				dataModule,
				networkModule,
				repositoryModule,
				useCaseModule,
				viewModelModule
			))
		}
	}
}