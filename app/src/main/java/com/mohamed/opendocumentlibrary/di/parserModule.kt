package com.mohamed.opendocumentlibrary.di

import com.mohamed.opendocumentlibrary.parser.IParser
import com.mohamed.opendocumentlibrary.parser.Parser
import org.koin.dsl.module

val parserModule = module {
	single<IParser> { Parser() }
}