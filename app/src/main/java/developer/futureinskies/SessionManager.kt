package developer.futureinskies

import android.content.Context
import android.preference.PreferenceManager
import android.support.v4.app.FragmentActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import developer.futureinskies.AlbumsList.DataModel.Albumsdata
import java.util.*

/**
 * Created by Nishanth on 29/1/19.
 */

object SessionManager {

    fun saveAlbumsArrayList(key: String, list: java.util.ArrayList<Albumsdata>?, cxt: Context) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(cxt)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()     // This line is IMPORTANT !!!
    }

    fun getAlbumsArrayList(key: String, cxt: FragmentActivity?): java.util.ArrayList<Albumsdata>? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(cxt)
        val gson = Gson()
        val json = prefs.getString(key, null)
        val type = object : TypeToken<ArrayList<Albumsdata>>() {

        }.type
        return gson.fromJson<ArrayList<Albumsdata>>(json, type)
    }
}
