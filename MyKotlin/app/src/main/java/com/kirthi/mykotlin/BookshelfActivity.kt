package com.kirthi.mykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kirthi.mykotlin.ui.main.BookshelfFragment

class BookshelfActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BookshelfFragment.newInstance())
                .commitNow()
        }
    }
}
