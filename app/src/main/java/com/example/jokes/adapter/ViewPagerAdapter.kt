package com.example.jokes.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jokes.JokesList
import com.example.jokes.R
import com.example.jokes.parser.CategoryParser
import java.util.ArrayList


class ViewPagerAdapter(private var c: Context, private var users: ArrayList<CategoryParser.Joke>) : PagerAdapter() {
    override fun isViewFromObject(v: View, `object`: Any): Boolean {
        return v === `object` as View
    }


    override fun getCount(): Int {
        return users.size
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.page_layout,parent,false)

//            val linearLayout: LinearLayout = view.findViewById(R.id.linearlayout)
        val categoryname: TextView = view.findViewById(R.id.categoryname)
        categoryname.text = users.get(position).getValue()[position]


        view.setOnClickListener {
            val intent = Intent(c, JokesList::class.java)
            intent.putExtra("Category", users.get(position).getValue()[position])
            ContextCompat.startActivity(c, intent, null)
        }

        parent.addView(view)
        return view
    }

    override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
        // Remove the view from view group specified position
        parent.removeView(`object` as View)
    }
}