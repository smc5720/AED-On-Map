package kau.msproject.searchaed.ui.youtube

import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kau.msproject.searchaed.R
import kau.msproject.searchaed.YoutubeItem
import kau.msproject.searchaed.YoutubeItemAdapter


class YoutubeFragment : Fragment() {

    companion object {
        fun newInstance() = YoutubeFragment()
    }

    private lateinit var youtubeViewModel: YoutubeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        youtubeViewModel = ViewModelProviders.of(this).get(YoutubeViewModel::class.java)
        val root = inflater.inflate(R.layout.youtube_fragment, container, false)

        val listView : ListView = root.findViewById(R.id.youtube_list)
        var youtubeItems = arrayOf(YoutubeItem(resources.getDrawable(R.drawable.cpr),"심폐소생술 교육영상", "2018-11-07 안전한국", "https://www.youtube.com/watch?v=zWv7xsm0Tu8"),
            YoutubeItem(resources.getDrawable(R.drawable.aed), "AED사용방법 교육영상","2017-12-10 안전한국","https://www.youtube.com/watch?v=ViZtrjdwY9I"),
            YoutubeItem(resources.getDrawable(R.drawable.chejo),"국민체조 영상", "2013-08-25 국민체육진흥공단","https://www.youtube.com/watch?v=wP5rGmrTyjg"),
            YoutubeItem(resources.getDrawable(R.drawable.honja),"혼자 있을때 심장마비가 온다면","2019-08-13", "https://www.youtube.com/watch?v=etHllPMBzr8"),
            YoutubeItem(resources.getDrawable(R.drawable.sonic), "심정지 유발 고슴도치", "2019-07-12 냥이아빠", "https://www.youtube.com/watch?v=jDRfIHP7HyE"),
            YoutubeItem(resources.getDrawable(R.drawable.haim), "하임리히법 교육영상","2019-03-20 쉐어하우스", "https://www.youtube.com/watch?v=Tt0QLa1zQM4")
        )
        var youtubeAdapter = YoutubeItemAdapter(root.context, youtubeItems)

        listView.adapter = youtubeAdapter
        listView.setOnItemClickListener{ parent, view, position, id ->
            val intent : Intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(youtubeItems[position].link))
            startActivity(intent)
        }
        return root
    }

    fun onBackPressed(){
        
    }

}
