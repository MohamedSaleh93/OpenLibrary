package com.mohamed.opendocumentlibrary.di

import com.mohamed.opendocumentlibrary.network.INetworkCall
import com.mohamed.opendocumentlibrary.network.NetworkCall
import org.koin.dsl.module

val networkModule = module {
	single<INetworkCall> { NetworkCall() }
}