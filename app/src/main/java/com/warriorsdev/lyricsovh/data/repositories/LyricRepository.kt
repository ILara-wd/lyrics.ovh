package com.warriorsdev.lyricsovh.data.repositories

import com.warriorsdev.lyricsovh.data.entities.Lyrics
import com.warriorsdev.lyricsovh.utils.UseCaseResult

interface LyricRepository {
    suspend fun getLyricsByArtistSong(artist: String, song: String): UseCaseResult<Lyrics>
}