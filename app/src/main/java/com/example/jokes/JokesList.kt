package com.example.jokes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.jokes.connect.JSONDownloader

class JokesList : AppCompatActivity() {
    private lateinit var jsonDownloader: JSONDownloader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes_list)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        val category:String = intent.getStringExtra("Category")
        var jsonURL = "http://api.icndb.com/jokes/random/10?limitTo=$category"

        Toast.makeText(this,"this is the category value = $category",Toast.LENGTH_SHORT).show()

        jsonDownloader = JSONDownloader(this@JokesList, jsonURL,"Jokes",recyclerView)
        jsonDownloader.execute()
    }
}
