package developer.futureinskies

import android.widget.TextView
import developer.futureinskies.AlbumsList.AlbumsActivity
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class AlbumsActivityTest {
    @Test
    fun titleText(){
        var activity = Robolectric.setupActivity(AlbumsActivity::class.java)
        val textView = activity.findViewById(R.id.tvTitle) as TextView
        assertNotNull("TextView is null", textView)
        assertTrue("TextView's text does not match.", "Albums By Accenture" == textView.text.toString())
    }
}
