package com.example.jokes.parser

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.jokes.adapter.RecycleJokeAdapter
import com.example.jokes.adapter.ViewPagerAdapter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

@Suppress("DEPRECATION")

class JokesParser(private var c: Context, private var jsonData: String, private var recyclerview: RecyclerView) : AsyncTask<Void, Void, Boolean>() {

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

            val llm = LinearLayoutManager(c)
            recyclerview.layoutManager = llm
            recyclerview.adapter = RecycleJokeAdapter(c, users)
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
            for (i in 0 until jo.getJSONArray("value").length()) {
                var ja= jo.getJSONArray("value")

                val temp: JSONObject = ja.get(i) as JSONObject
                val id = temp.get("id") as Int
                val jokes = temp.get("joke") as String
                val categories = temp.getJSONArray("categories")
                joke = Joke(id as Int,jokes.toString(), categories[0].toString())
                users.add(joke)
            }


            return true
        } catch (e: JSONException) {
            e.printStackTrace()
            return false
        }
    }
    class Joke(private var m_Id:Int, private var m_jokes:String, private var m_categories: String){


        fun getId(): Int {
            return m_Id
        }

        fun getJokes(): String {
            return m_jokes
        }

        fun getcategories(): String {
            return m_categories
        }

    }
}