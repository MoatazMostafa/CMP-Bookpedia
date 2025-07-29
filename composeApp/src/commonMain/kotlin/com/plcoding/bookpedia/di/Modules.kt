package com.plcoding.bookpedia.di

import com.plcoding.bookpedia.book.data.network.RemoteBookDataSource
import com.plcoding.bookpedia.book.data.network.RemoteBookDataSourceImpl
import com.plcoding.bookpedia.book.data.repository.BookRepositoryImpl
import com.plcoding.bookpedia.book.domain.repository.BookRepository
import com.plcoding.bookpedia.book.presentation.SharedBookViewModel
import com.plcoding.bookpedia.book.presentation.book_details.BookDetailViewModel
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import com.plcoding.bookpedia.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(engine = get()) }
    singleOf(::RemoteBookDataSourceImpl).bind<RemoteBookDataSource>()
    singleOf(::BookRepositoryImpl).bind<BookRepository>()

    // ViewModels
    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SharedBookViewModel)

}