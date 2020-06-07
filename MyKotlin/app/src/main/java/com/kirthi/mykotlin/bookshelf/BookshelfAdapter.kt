package com.kirthi.mykotlin.bookshelf

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.kirthi.mykotlin.R
import com.kirthi.mykotlin.model.BookshelfModel
import com.kirthi.mykotlin.reader.BookReader

class BookshelfAdapter(
    modelList: List<BookshelfModel>,
    context: Context
) : RecyclerView.Adapter<BookViewHolder?>() {
    private val dataModelList: List<BookshelfModel> = modelList
    private val mContext: Context = context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder { // Inflate out card list item
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item_layout, parent, false)
        // Return a new view holder
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: BookViewHolder,
        position: Int
    ) { // Bind data for the item at position
        holder.bindData(dataModelList[position], mContext)
    }

    override fun getItemCount(): Int {
        return dataModelList.size ?: 0
    }
}

class BookViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private var cardImageView: ImageView = itemView.findViewById(R.id.imageView)
    private var titleTextView: TextView = itemView.findViewById(R.id.card_title)
    private var subTitleTextView: TextView = itemView.findViewById(R.id.card_subtitle)
    private var readButton: MaterialButton = itemView.findViewById(R.id.action_read)
    fun bindData(dataModel: BookshelfModel, context: Context?) {
        cardImageView.setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                dataModel.bookBanner
            )
        )
        titleTextView.text = dataModel.title
        subTitleTextView.text = dataModel.subTitle
        readButton.setOnClickListener {
            context.startActivity(Intent(context, BookReader::class.java))
        }
    }

}