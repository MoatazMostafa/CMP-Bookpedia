package com.plcoding.bookpedia.book.presentation

import androidx.lifecycle.ViewModel
import com.plcoding.bookpedia.book.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedBookViewModel: ViewModel() {

    private val _selectedBook = MutableStateFlow<Book?>(null)
    var selectedBook = _selectedBook.asStateFlow()

    fun onSelectBook(book: Book?) {
        _selectedBook.value = book
    }
}