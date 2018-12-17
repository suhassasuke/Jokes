package com.example.jokes.parser

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import android.provider.CalendarContract
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.jokes.JokesList
import com.example.jokes.R
import com.example.jokes.adapter.ViewPagerAdapter
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

@Suppress("DEPRECATION")

class CategoryParser(private var c: Context, private var jsonData: String, private var viewPager: ViewPager) : AsyncTask<Void, Void, Boolean>() {

    private lateinit var pd: ProgressDialog
    private var users = ArrayList<Joke>()


    override fun onPreExecute() {
        super.onPreExecute()

        pd = ProgressDialog(c)
        pd.setTitle("Parse JSON")
        pd.setMessage("Parsing...Please wait")
        pd.show()
    }

    override fun doInBackground(vararg voids: Void): Boolean? {
        return parse()
    }

    override fun onPostExecute(isParsed: Boolean?) {
        super.onPostExecute(isParsed)

        pd.dismiss()
        if (isParsed!!) {
            //BIND
            viewPager.adapter = ViewPagerAdapter(c, users)
        } else {
            Toast.makeText(c, "Unable To Parse that data. ARE YOU SURE IT IS VALID JSON DATA? JsonException was raised. Check Log Output.", Toast.LENGTH_LONG).show()
            Toast.makeText(c, "THIS IS THE DATA WE WERE TRYING TO PARSE :  "+ jsonData, Toast.LENGTH_LONG).show()
        }
    }

    /*
    Parse JSON data
     */
    private fun parse(): Boolean {
        try {
            var jo= JSONObject(jsonData)

            users.clear()
            var joke: Joke

                val type = jo.getString("type")
                val value = jo.getJSONArray("value")

                val listdata = ArrayList<String>()
                val jArray = value
                if (true) {
                    for (j in 0 until jArray.length()) {
                        listdata.add(jArray.getString(j))
                        joke = Joke(type, listdata)
                        users.add(joke)
                    }
                }


            return true
        } catch (e: JSONException) {
            e.printStackTrace()
            return false
        }
    }
    class Joke(private var m_type:String, private var m_value: ArrayList<String>){


        fun getType(): String {
            return m_type
        }

        fun getValue(): ArrayList<String> {
            return m_value
        }

    }
}