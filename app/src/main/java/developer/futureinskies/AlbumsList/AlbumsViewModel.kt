package developer.futureinskies.AlbumsList

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import developer.futureinskies.AlbumsList.DataModel.Albumsdata
import developer.futureinskies.Constants
import developer.futureinskies.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by Nishanth on 29/1/19.
 */

class AlbumsViewModel(context: Application) : AndroidViewModel(context) {

    private val context: Context = context.applicationContext //Application Context to avoid leaks.
    val userList = MutableLiveData<ArrayList<Albumsdata>>()

    fun getAlbums() {
        val call = Constants.client.getAlbums()
        call.enqueue(object : Callback<ArrayList<Albumsdata>> {
            override fun onResponse(call: Call<ArrayList<Albumsdata>>, response: Response<ArrayList<Albumsdata>>) {
                if (response.body() != null) {
                    Collections.sort(response.body(), object : Comparator<Albumsdata> {
                        override fun compare(lhs: Albumsdata, rhs: Albumsdata): Int {
                            return lhs.title!!.compareTo(rhs.title!!)
                        }
                    })
                    SessionManager.saveAlbumsArrayList("AlbumsList", response.body(), context)
                    userList.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<Albumsdata>>, t: Throwable) {
            }
        })
    }
}
