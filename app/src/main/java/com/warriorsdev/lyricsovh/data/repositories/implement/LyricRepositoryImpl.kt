package com.warriorsdev.lyricsovh.data.repositories.implement

import com.google.gson.Gson
import com.warriorsdev.lyricsovh.data.entities.Lyrics
import com.warriorsdev.lyricsovh.data.remote.LyricApi
import com.warriorsdev.lyricsovh.data.repositories.LyricRepository
import com.warriorsdev.lyricsovh.utils.UseCaseResult
import retrofit2.HttpException

class LyricRepositoryImpl(private val lyricApi: LyricApi) : LyricRepository {

    override suspend fun getLyricsByArtistSong(artist: String, song: String): UseCaseResult<Lyrics> {
        return try {
            val result = lyricApi.getLyricAsync(artist, song).await()
            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            val errorLyrics = Gson().fromJson((ex as HttpException).response()?.errorBody()?.string(),
                Lyrics::class.java)
            if (errorLyrics != null){
                UseCaseResult.Error(errorLyrics.error.toString())
            }else{
                UseCaseResult.Error(ex.message())
            }
        }
    }

}
