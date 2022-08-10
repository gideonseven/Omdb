package com.don.omdb.ui.detail

import android.annotation.SuppressLint
import android.widget.LinearLayout
import androidx.lifecycle.ViewModel
//import com.don.omdb.api.OmdbApi

/**
 * Created by gideon on 03,December,2019
 * dunprek@gmail.com
 * Jakarta - Indonesia
 */
@SuppressLint("StaticFieldLeak")
class DetailViewModel : ViewModel() {

//    private val omdbRepository: OmdbRepository = OmdbRepository.getInstance(RemoteRepository())!!

//    private lateinit var mOmdbApi: OmdbApi
    private lateinit var mProgress: LinearLayout
    var imdbID: String? = null


/*    fun getErrors(): LiveData<String> {
        return omdbRepository.getError()
    }

    fun getDetail(): LiveData<MdlDetail> {
        return omdbRepository.getDetails(mOmdbApi, imdbID, mProgress)
    }*/

  /*  fun setAttributes(omdbApi: OmdbApi, imdbID: String?, view: LinearLayout) {
        this.mOmdbApi = omdbApi
        this.imdbID = imdbID
        this.mProgress = view
    }*/
}
