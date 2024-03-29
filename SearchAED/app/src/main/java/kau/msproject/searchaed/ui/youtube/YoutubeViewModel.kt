package kau.msproject.searchaed.ui.youtube

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class YoutubeViewModel : ViewModel() {
    private val cpr_link = MutableLiveData<String>().apply {
        value = "심폐소생술(CPR) 진행 방법"
    }
    private val aed_link = MutableLiveData<String>().apply {
        value = "자동제세동기(AED) 사용 방법"
    }
}