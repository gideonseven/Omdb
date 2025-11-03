package com.don.omdb.ui.main


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.don.omdb.R
import com.don.omdb.data.remote.MdlMovieList
import com.don.omdb.databinding.ActivityMainBinding
import com.don.omdb.ui.BaseActivity
import com.don.omdb.utils.OnLoadMoreListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    lateinit var mAdapter: MainAdapter
    lateinit var progressDialog: LinearLayout
    private val mainViewModel: MainViewModel by viewModels()

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
        mainViewModel.setAttributes(currentPage, myQuery, progressDialog)

        // Avoid stacking observers when setAttributes is called multiple times.
        mainViewModel.getMovies().removeObservers(this)
        mainViewModel.getErrors().removeObservers(this)

        mainViewModel.getMovies().observe(this, getMovie)
        mainViewModel.getErrors().observe(this, getError)
    }

    private fun setupUI() {
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
                        // Refresh attributes and re-attach a single observer
                        mainViewModel.setAttributes(currentPage, myQuery, progressDialog)
                        mainViewModel.getMovies().removeObservers(this@MainActivity)
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
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // reset list on adapter
                resetState(query)
                mainViewModel.setAttributes(currentPage, myQuery, progressDialog)
                mainViewModel.getMovies().removeObservers(this@MainActivity)
                mainViewModel.getMovies().observe(this@MainActivity, getMovie)

                // hide keyboard safely (avoid NPE on currentFocus)
                (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(currentFocus?.windowToken, 0)

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean = false
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

    private val getError = Observer<String> { msg ->
        if (msg != null) {
            Timber.d(msg)
            showSnackBar(msg)
        }
    }
}
