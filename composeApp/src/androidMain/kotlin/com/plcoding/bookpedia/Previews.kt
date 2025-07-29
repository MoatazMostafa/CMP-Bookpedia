package com.plcoding.bookpedia

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreen
import com.plcoding.bookpedia.book.presentation.book_list.BookListState

@Preview
@Composable
fun BookListScreenPreview(){
    MaterialTheme {
        BookListScreen(
            state = BookListState(searchResult = books),
            onAction = {}
        )
    }
}


private val books = (1..100).map{
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://example.com/book$it.jpg",
        authors = listOf("SpongeBob Squarepants"),
        description = "This is a description of book $it. It is a very interesting book that you should read.",
        firstPublishYear = "2023",
        languages = listOf("English"),
        averageRating = 4.5,
        ratingsCount = 5,
        numPages = 100,
        numEditions = 10,
    )
}

