package com.plcoding.bookpedia.book.data.mapper

import com.plcoding.bookpedia.book.data.dto.SearchedBookDto
import com.plcoding.bookpedia.book.domain.Book

fun SearchedBookDto.toBook(): Book =
    Book(
        id = id.substringAfterLast('/'),
        title = title,
        authors = authorNames ?: emptyList(),
        imageUrl = if (coverKey != null) "https://covers.openlibrary.org/b/olid/$coverKey-L.jpg"
        else "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg",
        description = null,
        languages = languages?: emptyList(),
        firstPublishYear = firstPublishYear.toString(),
        averageRating = ratingsAverage,
        ratingsCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions = numEditions?: 0,
    )