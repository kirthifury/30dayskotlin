package com.kirthi.mykotlin.ui.main

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirthi.mykotlin.R
import com.kirthi.mykotlin.model.BookshelfModel

class BookshelfViewModel : ViewModel() {
    var state: BookshelfViewState = BookshelfViewState.Idle
    private val _stateLiveData = MutableLiveData<BookshelfViewState>(
        BookshelfViewState.Idle
    )
    var bookList = MutableLiveData<ArrayList<BookshelfModel>>()
    val stateLiveData: LiveData<BookshelfViewState> = _stateLiveData
    val bookTitle = arrayOf(
        "The Adventures of Sherlock Holmes",
        "The Memoirs of Sherlock Holmes",
        "South Sea Tales",
        "The Game",
        "Tales of Space and Time"
    )
    val subTitle = arrayOf(
        "Arthur Conan Doyle",
        "Arthur Conan Doyle",
        " Jack London",
        "Jack London",
        "H. G. Wells"
    )
    val bookCover = arrayOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5
    )

    init {
        val dataModelList: ArrayList<BookshelfModel> =
            ArrayList<BookshelfModel>()
        for (i in 0..4) {
            dataModelList.add(BookshelfModel(bookCover[i], bookTitle[i], subTitle[i]))
        }
        bookList.value = dataModelList
    }

    fun onDownloadClick() {
        _stateLiveData.value = BookshelfViewState.Progress
        downloadBook { hasSucceed ->
            if (hasSucceed) {
                _stateLiveData.value = BookshelfViewState.Succeed(
                    message = "Book Downloaded Successfully"
                )
            }
        }
    }

    fun getAllBooks(): MutableLiveData<ArrayList<BookshelfModel>> {
        return bookList
    }

    private fun downloadBook(callback: (Boolean) -> Unit) {
        Handler().postDelayed({
            callback(true)
        }, 5000)
    }
}

sealed class BookshelfViewState {
    object Idle : BookshelfViewState()
    object Progress : BookshelfViewState()
    object Active : BookshelfViewState()
    object InActive : BookshelfViewState()
    data class Failed(val message: String) : BookshelfViewState()
    data class Succeed(val message: String) : BookshelfViewState()

}
