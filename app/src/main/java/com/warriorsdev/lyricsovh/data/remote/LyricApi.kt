package com.warriorsdev.lyricsovh.data.remote

import com.warriorsdev.lyricsovh.data.entities.Lyrics
import com.warriorsdev.lyricsovh.data.entities.SongList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface LyricApi {

    @GET("suggest/{search_term}")
    fun getSongAsync(@Path("search_term") term: String): Deferred<SongList>

    @GET("v1/{Artist}/{Song}")
    fun getLyricAsync(@Path("Artist") artist: String, @Path("Song") song: String): Deferred<Lyrics>

}