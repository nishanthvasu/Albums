package developer.futureinskies.AlbumsList

import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import developer.futureinskies.AlbumsList.DataModel.Albumsdata
import developer.futureinskies.Constants
import developer.futureinskies.R
import developer.futureinskies.SessionManager
import developer.futureinskies.databinding.AdapterAlbumsBinding
import developer.futureinskies.databinding.FragmentAlbumlistBinding
import java.util.*


/**
 * Created by Nishanth on 29/1/19.
 */

class AlbumsFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentAlbumlistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_albumlist, container, false)

        viewDataBinding = FragmentAlbumlistBinding.bind(root).apply {
            viewmodel = (activity as AlbumsActivity).obtainViewModel()
        }
        setHasOptionsMenu(true)
        retainInstance = false
        return viewDataBinding.root
    }

    companion object {
        fun newInstance(): AlbumsFragment {
            return AlbumsFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val customfontTitle = Typeface.createFromAsset(activity!!.assets, "fonts/GOTHICBI.TTF")
        viewDataBinding.tvTitle.typeface = customfontTitle

        if (Constants.isNetworkAvailable(activity!!)) {
            viewDataBinding.viewmodel?.getAlbums()
            viewDataBinding.viewmodel?.userList!!.observe(this, Observer {
                it?.run {
                    viewDataBinding.rvAlbums.adapter = UserAdapter(it, activity)
                    viewDataBinding.rvAlbums.layoutManager = LinearLayoutManager(activity)
                }
            })
        } else {
            viewDataBinding.rvAlbums.adapter = UserAdapter(SessionManager.getAlbumsArrayList("AlbumsList", activity)!!, activity)
            viewDataBinding.rvAlbums.layoutManager = LinearLayoutManager(activity)
        }
    }

    inner class UserAdapter(albList: ArrayList<Albumsdata>, context: FragmentActivity?) : RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder>() {

        private var albumList = java.util.ArrayList<Albumsdata>()
        private var context: Context

        init {
            this.albumList = albList
            this.context = context!!
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapterViewHolder {
            return UserAdapterViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.adapter_albums, parent, false))
        }

        override fun onBindViewHolder(holder: UserAdapterViewHolder, position: Int) {
            holder.bindUser(albumList[position], position)
        }

        override fun getItemCount(): Int {
            return albumList.size
        }

        inner class UserAdapterViewHolder(private var mAlbumsBinding: AdapterAlbumsBinding) : RecyclerView.ViewHolder(mAlbumsBinding.root) {

            internal fun bindUser(albums: Albumsdata, position: Int) {

                if (position % 2 == 0)
                    mAlbumsBinding.tvAlbumName.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.colorwhite))
                else
                    mAlbumsBinding.tvAlbumName.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.colorveryLightBlue))

                val customfont = Typeface.createFromAsset(activity!!.assets, "fonts/CenturyGothic.ttf")
                mAlbumsBinding.tvAlbumName.typeface = customfont
                val upperString = albums.title!!.substring(0, 1).toUpperCase() + albums.title!!.substring(1)
                mAlbumsBinding.tvAlbumName.text = upperString
            }
        }
    }
}
