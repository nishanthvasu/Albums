package developer.futureinskies.AlbumsList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import developer.futureinskies.R
import developer.futureinskies.obtainViewModel
import developer.futureinskies.replaceFragmentInActivity

/**
 * Created by Nishanth on 29/1/19.
 */

class AlbumsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addtask_act)
        replaceFragmentInActivity(obtainViewFragment(), R.id.lay_fr_container)
    }

    private fun obtainViewFragment(): Fragment {
        return supportFragmentManager.findFragmentById(R.id.lay_fr_container)
                ?: AlbumsFragment.newInstance().apply { }
    }

    fun obtainViewModel(): AlbumsViewModel = obtainViewModel(AlbumsViewModel::class.java)
    override fun onBackPressed() {
        finishAffinity()
    }
}