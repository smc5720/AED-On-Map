package kau.msproject.searchaed.ui.emergency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kau.msproject.searchaed.EmergencyActivity2
import kau.msproject.searchaed.R

class SecondFragment(address : String) : Fragment(){

    companion object {
        fun newInstance(address : String) = SecondFragment(address)
    }
    var address = address

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_second, container, false)

        var location = root.findViewById<TextView>(R.id.text_location)
        location.text = address

        return root
    }
}