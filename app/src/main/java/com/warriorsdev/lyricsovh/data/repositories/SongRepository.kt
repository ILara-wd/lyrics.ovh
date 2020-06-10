package com.warriorsdev.lyricsovh.data.repositories

import com.warriorsdev.lyricsovh.data.entities.Song
import com.warriorsdev.lyricsovh.utils.UseCaseResult

interface SongRepository {
    suspend fun getSongListByTerm(term: String): UseCaseResult<List<Song>>
}
