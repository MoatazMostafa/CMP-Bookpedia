package com.plcoding.bookpedia.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.plcoding.bookpedia.book.presentation.SharedBookViewModel
import com.plcoding.bookpedia.book.presentation.book_details.BookDetailAction
import com.plcoding.bookpedia.book.presentation.book_details.BookDetailScreenRoot
import com.plcoding.bookpedia.book.presentation.book_details.BookDetailViewModel
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.BookGraph
        ) {
            navigation<Route.BookGraph>(
                startDestination = Route.BookList
            ) {
                composable<Route.BookList> {
                    val viewModel = koinViewModel<BookListViewModel>()
                    val sharedBookViewModel =
                        it.sharedKoinViewModel<SharedBookViewModel>(navController)
                    LaunchedEffect(true) {
                        sharedBookViewModel.onSelectBook(null)
                    }

                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = { book ->
                            sharedBookViewModel.onSelectBook(book = book)
                            navController.navigate(Route.BookDetails(book.id))
                        },
                    )
                }
                composable<Route.BookDetails> {
                    val sharedBookViewModel =
                        it.sharedKoinViewModel<SharedBookViewModel>(navController)
                    val viewModel = koinViewModel<BookDetailViewModel>()
                    val selectedBook by sharedBookViewModel.selectedBook.collectAsStateWithLifecycle()
                    LaunchedEffect(selectedBook) {
                        selectedBook?.let {
                            viewModel.onAction(BookDetailAction.OnSelectedBookChange(it))
                        }
                    }
                    BookDetailScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.navigateUp() }
                    )
                }
            }
        }

    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRout = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRout)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}