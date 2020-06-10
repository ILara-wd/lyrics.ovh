package com.warriorsdev.lyricsovh.module

import com.warriorsdev.lyricsovh.data.remote.LyricApi
import com.warriorsdev.lyricsovh.data.remote.RetrofitFactory.createHttpClient
import com.warriorsdev.lyricsovh.data.remote.RetrofitFactory.createWebService
import com.warriorsdev.lyricsovh.data.repositories.LyricRepository
import com.warriorsdev.lyricsovh.data.repositories.SongRepository
import com.warriorsdev.lyricsovh.data.repositories.implement.LyricRepositoryImpl
import com.warriorsdev.lyricsovh.data.repositories.implement.SongRepositoryImpl
import com.warriorsdev.lyricsovh.presentation.detail.DetailViewModel
import com.warriorsdev.lyricsovh.presentation.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

const val LYRICS_API_BASE_URL = "https://api.lyrics.ovh/"

val appModules = module {
    single {
        createWebService<LyricApi>(
            okHttpClient = createHttpClient(),
            factory = RxJava2CallAdapterFactory.create(),
            baseUrl = LYRICS_API_BASE_URL
        )
    }
    factory<SongRepository> {
        SongRepositoryImpl(
            lyricApi = get()
        )
    }
    factory<LyricRepository> { LyricRepositoryImpl(lyricApi = get()) }

    viewModel { MainViewModel(songRepository = get()) }
    viewModel { DetailViewModel(lyricRepository = get()) }
}
