package com.warriorsdev.lyricsovh.utils

sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val exception: String) : UseCaseResult<Nothing>()
}