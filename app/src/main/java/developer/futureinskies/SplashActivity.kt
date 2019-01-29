package developer.futureinskies

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.view.Window
import developer.futureinskies.AlbumsList.AlbumsActivity
import kotlinx.android.synthetic.main.dialog_networkalert.*


/**
 * Created by Nishanth on 29/1/19.
 */

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed({

            if (SessionManager.getAlbumsArrayList("AlbumsList", this) == null) {
                if (Constants.isNetworkAvailable(applicationContext))
                    startActivity(Intent(this, AlbumsActivity::class.java))
                else {
                    val alert = Dialog(this)
                    alert.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    alert.setContentView(R.layout.dialog_networkalert)
                    alert.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    alert.setCanceledOnTouchOutside(false)
                    alert.setCancelable(false)
                    alert.show()

                    alert.btnReload.setOnClickListener {
                        if (Constants.isNetworkAvailable(applicationContext))
                            startActivity(Intent(this, AlbumsActivity::class.java))
                    }

                }
            } else
                startActivity(Intent(this, AlbumsActivity::class.java))
        }, 2000)
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
