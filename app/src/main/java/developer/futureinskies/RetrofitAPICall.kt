package developer.futureinskies

import developer.futureinskies.AlbumsList.DataModel.Albumsdata
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Nishanth on 29/1/19.
 */
interface RetrofitAPICall {
    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
     */

    @GET("albums/")
    fun getAlbums(): Call<ArrayList<Albumsdata>>
}