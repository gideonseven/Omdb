package com.don.omdb.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.don.omdb.R
//import com.don.omdb.api.OmdbApi
import com.don.omdb.data.remote.MdlDetail
import com.don.omdb.databinding.ActivityDetailBinding
import com.don.omdb.ui.BaseActivity
import com.don.omdb.utils.GlideUtil
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BaseActivity() {
    companion object {
        const val EXTRA_IMDB = "extra_imdb"
    }

//    @Inject
//    lateinit var omdbApi: OmdbApi
    private lateinit var progressDialog: LinearLayout
    private lateinit var binding: ActivityDetailBinding

    private var imdbID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()

        setupVM()
    }

    private fun setupUI() {
        setToolbarClose("Detail Movie")
        progressDialog = findViewById(R.id.progress_dialog)
        progressDialog.visibility = View.VISIBLE
        imdbID = intent.extras?.getString(EXTRA_IMDB)
    }

    private fun setupVM() {
        val detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
//        detailViewModel.setAttributes(omdbApi, imdbID, progressDialog)
//        detailViewModel.getErrors().observe(this, getError)
//        detailViewModel.getDetail().observe(this, getDetail)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setMovieDetail(mdlDetail: MdlDetail) {
        GlideUtil.glideOverrideSize(this, mdlDetail.poster!!, binding.ivPoster)
        binding.tvMovieTitle.text = mdlDetail.title
        binding.tvRating.text = mdlDetail.imdbRating
        binding.tvDate.text = mdlDetail.year
        binding.tvOverView.text = mdlDetail.plot
    }

    private val getDetail = Observer<MdlDetail> { detail ->
        if (detail != null) {
            Timber.d(detail.toString())
            setMovieDetail(detail)
        }
    }

    private val getError = Observer<String> { list ->
        if (list != null) {
            Timber.d(list)
            showSnackBar(list)
        }
    }

}
