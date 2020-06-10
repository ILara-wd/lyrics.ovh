package com.warriorsdev.lyricsovh.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.warriorsdev.lyricsovh.R
import com.warriorsdev.lyricsovh.data.entities.Song
import com.warriorsdev.lyricsovh.presentation.loadImage
import com.warriorsdev.lyricsovh.utils.Tools
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

const val EXTRA_SONG_ITEM = "EXTRA_SONG_ITEM"

class DetailActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context, song: Song): Intent {
            return Intent(context, DetailActivity::class.java)
                .putExtra(EXTRA_SONG_ITEM, song)
        }
    }

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        song = intent.getSerializableExtra(EXTRA_SONG_ITEM) as Song
        initView()
        initViewModel()
    }

    private fun initView() {
        tvArtistName.text = song.artist?.name
        tvSongName.text = song.title
        tvAlbumName.text = song.album?.title
        ivAlbum.loadImage(song.album?.cover.toString())
    }

    private fun initViewModel() {
        if (Tools.isNetworkConnected(this@DetailActivity)){
            viewModel.loadLyrics(song = song.title_short.toString(), artist = song.artist?.name.toString())
        }else{
            Toast.makeText(this, getString(R.string.app_name), Toast.LENGTH_SHORT).show()
        }
        viewModel.lyrics.observe(this, Observer { lyric ->
            tvLyric.text = lyric.lyrics
        })
        viewModel.showError.observe(this, Observer { showError ->
            tvLyric.text = showError
        })
    }

}