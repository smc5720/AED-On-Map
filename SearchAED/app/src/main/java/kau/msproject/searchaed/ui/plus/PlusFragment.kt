package kau.msproject.searchaed.ui.plus

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import kau.msproject.searchaed.R

class PlusFragment : Fragment() {

    companion object {
        fun newInstance() = PlusFragment()
    }

    private lateinit var viewModel: PlusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.plus_fragment, container, false)

        val img = root.findViewById<ImageView>(R.id.img_korea_cpr)
        img.setOnClickListener{
            val intent : Intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("http://www.kacpr.org/"))
            startActivity(intent)
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlusViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
