package com.example.jokes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.jokes.connect.JSONDownloader

class MainActivity : AppCompatActivity() {
    private lateinit var jsonDownloader: JSONDownloader


    private var jsonURL = "http://api.icndb.com/categories"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gv = findViewById<ViewPager>(R.id.viewpager)

//        val downloadBtn = findViewById<Button>(R.id.downloadBtn)
        jsonDownloader = JSONDownloader(this@MainActivity, jsonURL,"categories",gv)
        jsonDownloader.execute()

    }
}
