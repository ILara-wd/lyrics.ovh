package com.warriorsdev.lyricsovh.data.repositories.implement

import com.warriorsdev.lyricsovh.utils.UseCaseResult
import com.warriorsdev.lyricsovh.data.entities.Song
import com.warriorsdev.lyricsovh.data.remote.LyricApi
import com.warriorsdev.lyricsovh.data.repositories.SongRepository

class SongRepositoryImpl(private val lyricApi: LyricApi) :
    SongRepository {
    override suspend fun getSongListByTerm(term: String): UseCaseResult<List<Song>> {
        return try {
            val result = lyricApi.getSongAsync(term = term).await()
            UseCaseResult.Success(result.data.orEmpty())
        } catch (ex: Exception) {
            UseCaseResult.Error(ex.message.toString())
        }
    }
}
