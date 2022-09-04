package com.don.omdb.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.don.omdb.MovieApp
import com.don.omdb.R
import com.don.omdb.api.MovieService
import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.databinding.ActivityMainBinding
import com.don.omdb.ui.BaseActivity
import com.don.omdb.utils.OnLoadMoreListener
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var movieService: MovieService
    lateinit var mAdapter: MainAdapter
    lateinit var progressDialog: LinearLayout
    lateinit var mainViewModel: MainViewModel

    lateinit var binding: ActivityMainBinding

    private var totalPage: Int = 4
    private var currentPage = 1
    private var myQuery = "dragon"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup Ui
        setupUI()

        //setup vm
        setupVM()

        //setup adapter
        setupAdapter()
    }


    private fun setupVM() {
        (application as MovieApp).appComponent.inject(this)
        mainViewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        mainViewModel.setAttributes(movieService, currentPage, myQuery, progressDialog)
        mainViewModel.getMovies().observe(this, getMovie)
        mainViewModel.getErrors().observe(this, getError)
    }

    private fun setupUI() {
        //setup toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)
        progressDialog = findViewById(R.id.progress_dialog)
    }

    private fun setupAdapter() {
        mAdapter = MainAdapter(this)
        mAdapter.setLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                binding.rvMovieList.post {
                    currentPage++
                    if (currentPage > totalPage) {
                        mAdapter.setMoreDataAvailable(false)
                    } else {
                        progressDialog.visibility = View.VISIBLE
                        mainViewModel.setAttributes(
                            movieService,
                            currentPage,
                            myQuery,
                            progressDialog
                        )
                        mainViewModel.getMovies().observe(this@MainActivity, getMovie)
                    }
                }
            }
        })
        binding.rvMovieList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //search manager
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //reset list on adapter
                resetState(query)
                mainViewModel.setAttributes(movieService, currentPage, myQuery, progressDialog)
                mainViewModel.getMovies().observe(this@MainActivity, getMovie)
                //hide keyboard
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(
                    Objects.requireNonNull<View>(currentFocus).windowToken,
                    0
                )
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        searchView.setOnCloseListener { false }
        return true
    }

    private fun resetState(query: String) {
        currentPage = 1
        myQuery = query
        mAdapter.clearList()
    }

    private val getMovie = Observer<List<MdlMovieList>> { list ->
        if (list != null) {
            Timber.d(list.toString())
            mAdapter.setData(list)
        }
    }

    private val getError = Observer<String> { list ->
        if (list != null) {
            Timber.d(list)
            showSnackBar(list)
        }
    }

}
