package com.kirthi.mykotlin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kirthi.mykotlin.R
import com.kirthi.mykotlin.bookshelf.BookshelfAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class BookshelfFragment : Fragment() {

    companion object {
        fun newInstance() = BookshelfFragment()
    }

    private val viewModel: BookshelfViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        bookshelfView.layoutManager = linearLayoutManager
        viewModel.onDownloadClick()
        viewModel.stateLiveData.observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState) {
                BookshelfViewState.Idle -> {

                }
                BookshelfViewState.Progress -> {

                }
                is BookshelfViewState.Succeed -> {

                }
                is BookshelfViewState.Failed -> {

                }
            }
        })
        viewModel.getAllBooks().observe(viewLifecycleOwner, Observer { booksList ->
            context?.let {
                bookshelfView.adapter = BookshelfAdapter(booksList, it)
                bookshelfView?.adapter?.notifyDataSetChanged()
            }

        })
        viewModel.getAllBooks()
    }


}
