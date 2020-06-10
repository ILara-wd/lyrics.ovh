package com.warriorsdev.lyricsovh.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.warriorsdev.lyricsovh.R
import com.warriorsdev.lyricsovh.data.entities.Song
import com.warriorsdev.lyricsovh.presentation.detail.DetailActivity
import com.warriorsdev.lyricsovh.presentation.main.adapter.SongAdapter
import com.warriorsdev.lyricsovh.utils.Tools
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var songAdapter: SongAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onSongClicked: (song: Song) -> Unit = { song -> viewModel.songClicked(song) }
        songAdapter = SongAdapter(onSongClicked)
        songRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = songAdapter
        }
        initViewModel()

    }

    private fun initViewModel() {
        viewModel.songsList.observe(this, Observer { newSongList ->
            songAdapter.updateData(newSongList.orEmpty())
        })
        viewModel.showLoading.observe(this, Observer { showLoading ->
            mainProgressBar.visibility = if (showLoading!!) View.VISIBLE else View.GONE
        })
        viewModel.showError.observe(this, Observer { showError ->
            Toast.makeText(this, showError, Toast.LENGTH_SHORT).show()
        })
        viewModel.navigateToDetail.observe(this, Observer {
            if (it != null) startActivity(DetailActivity.getStartIntent(this, it))
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()){
                    if (Tools.isNetworkConnected(this@MainActivity)){
                        viewModel.loadSong(newText)
                    }else{
                        Toast.makeText(this@MainActivity, getString(R.string.app_name), Toast.LENGTH_SHORT).show()
                    }
                }
                return false
            }
        })
        return true
    }

}