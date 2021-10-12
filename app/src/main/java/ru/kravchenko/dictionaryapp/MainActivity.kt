package ru.kravchenko.dictionaryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"

    val word_edit_text: EditText = findViewById(R.id.word_edit_text)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val queue = Volley.newRequestQueue(this)
        val find_button: Button = findViewById(R.id.find_button)

        find_button.setOnClickListener {
            val url = getUrl()


            val stringRequest = StringRequest(Request.Method.GET, url,
                { responce ->
                    try {
                        extractDefinitionFromJson(responce)
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                    }

                },
                { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()

                }
            )
            queue.add(stringRequest)
        }
    }


    private fun getUrl(): String {

        val word = word_edit_text.text
        val apiKey = "ae46dcaa-b4d7-4714-89c8-d3e9931f6add"
        val url = "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"
        return url
    }


    private fun extractDefinitionFromJson(response: String) {
        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShortDefinition = firstIndex.getJSONArray("shortdef")
        val firstShortDefinition = getShortDefinition.get(0)

        val intent = Intent(this, WordDefinitionActivity::class.java)
        intent.putExtra(KEY, firstShortDefinition.toString())
        startActivity(intent)

    }
}