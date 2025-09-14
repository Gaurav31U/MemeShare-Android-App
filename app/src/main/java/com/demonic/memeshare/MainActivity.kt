package com.demonic.memeshare

import android.app.DownloadManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loadMeme()
    }
    private fun loadMeme(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val url = response.getString("url")
                val memeImageView = findViewById<ImageView>(R.id.memeImageView)
                Glide.with(this).load(url).into(memeImageView)
            },
            { error ->

            }
        )
        queue.add(jsonObjectRequest)
    }
    fun shareMeme(view: View) {}
    fun nextMeme(view: View) {
        loadMeme()
    }
}