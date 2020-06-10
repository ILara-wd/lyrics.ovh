package com.warriorsdev.lyricsovh.presentation.main

import androidx.lifecycle.MutableLiveData
import com.warriorsdev.lyricsovh.data.entities.Song
import com.warriorsdev.lyricsovh.data.repositories.SongRepository
import com.warriorsdev.lyricsovh.presentation.base.BaseViewModel
import com.warriorsdev.lyricsovh.utils.SingleLiveEvent
import com.warriorsdev.lyricsovh.utils.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val songRepository: SongRepository) : BaseViewModel() {

    val showLoading = MutableLiveData<Boolean>()
    val songsList = MutableLiveData<List<Song>>()
    val showError = SingleLiveEvent<String>()
    val navigateToDetail = SingleLiveEvent<Song>()

    fun loadSong(text: String) {
        showLoading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { songRepository.getSongListByTerm(term = text) }
            showLoading.value = false
            when (result) {
                is UseCaseResult.Success -> songsList.value = result.data
                is UseCaseResult.Error -> showError.value = result.exception
            }
        }
    }

    fun songClicked(song: Song) {
        navigateToDetail.value = song
    }

}
