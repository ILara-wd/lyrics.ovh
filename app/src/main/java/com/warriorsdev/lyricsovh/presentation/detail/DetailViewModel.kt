package com.warriorsdev.lyricsovh.presentation.detail

import androidx.lifecycle.MutableLiveData
import com.warriorsdev.lyricsovh.data.entities.Lyrics
import com.warriorsdev.lyricsovh.data.repositories.LyricRepository
import com.warriorsdev.lyricsovh.presentation.base.BaseViewModel
import com.warriorsdev.lyricsovh.utils.SingleLiveEvent
import com.warriorsdev.lyricsovh.utils.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val lyricRepository: LyricRepository) : BaseViewModel() {

    val lyrics = MutableLiveData<Lyrics>()
    val showError = SingleLiveEvent<String>()

    fun loadLyrics(artist: String, song: String) {
        launch {
            val result = withContext(Dispatchers.IO) {
                lyricRepository.getLyricsByArtistSong(artist = artist, song = song)
            }
            when (result) {
                is UseCaseResult.Success -> lyrics.value = result.data
                is UseCaseResult.Error -> showError.value = result.exception
            }
        }
    }

}