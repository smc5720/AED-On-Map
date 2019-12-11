package kau.msproject.searchaed

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class YoutubeItemAdapter(val context : Context,val youtubeItems : Array<YoutubeItem>) : BaseAdapter(){

    override fun getCount(): Int {
        return youtubeItems.size
    }

    override fun getItem(idx: Int): Any {
        return youtubeItems[idx]
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(idx : Int): Long {
        return idx.toLong()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getView(idx : Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.youtube_item, parent,false)
        view.findViewById<ImageView>(R.id.img).setImageDrawable(youtubeItems[idx].img)
        view.findViewById<TextView>(R.id.title).text = youtubeItems[idx].title
        view.findViewById<TextView>(R.id.context).text = youtubeItems[idx].context
        return view
    }
}