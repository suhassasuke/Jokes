package com.example.jokes.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.jokes.R
import com.example.jokes.parser.JokesParser

class RecycleJokeAdapter(private val mContext: Context, private var users: ArrayList<JokesParser.Joke>) :
    RecyclerView.Adapter<RecycleJokeAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        //the video_item.xml file is now associated as view object
        //so the view can be called from view's object
        var jokeid = view.findViewById<TextView>(R.id.jokeid)
        var jokecategory = view.findViewById<TextView>(R.id.jokecategory)
        var jokevalue = view.findViewById<TextView>(R.id.jokevalue)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        // create a new view
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.jokes_layout, parent, false)

        return MyViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    //filling every item of view with respective text and image
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val singleJoke = users[position]

        //replace the default text with id, title and description with setText method
        holder.jokecategory.setText(singleJoke.getcategories())
        holder.jokeid.setText(singleJoke.getId().toString())
        holder.jokevalue.setText(singleJoke.getJokes())

    }

    // Return the size of your dataset (invoked by the layout manager)
    //here the dataset is mVideoList
    override fun getItemCount(): Int {
        return users.size
    }
}