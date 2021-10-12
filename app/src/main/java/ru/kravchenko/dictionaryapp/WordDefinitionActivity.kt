package ru.kravchenko.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class WordDefinitionActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_definition)

        val back_image_view: ImageView = findViewById(R.id.back_image_view)
        val definition_text_view: TextView = findViewById(R.id.definition_text_view)

        definition_text_view.text = intent.getStringExtra(KEY)

        back_image_view.setOnClickListener { finish() }

    }
}