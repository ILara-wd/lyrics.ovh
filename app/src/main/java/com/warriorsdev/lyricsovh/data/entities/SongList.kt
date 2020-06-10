package com.warriorsdev.lyricsovh.data.entities

import java.io.Serializable

class SongList: Serializable {
    var data: List<Song>? = null
}

class Song: Serializable {
    var id: Int? = null
    var readable: Boolean? = null
    var title: String? = null
    var title_short: String? = null
    var title_version: String? = null
    var link: String? = null
    var duration: Int? = null
    var rank: Int? = null
    var explicit_lyrics: Boolean? = null
    var explicit_content_lyrics: Int? = null
    var explicit_content_cover: Int? = null
    var preview: String? = null
    var artist: Artist? = null
    var album: Album? = null
    var type: String? = null
}

class Artist: Serializable {
    var id: Int? = null
    var name: String? = null
    var link: String? = null
    var picture: String? = null
    var picture_small: String? = null
    var picture_medium: String? = null
    var picture_big: String? = null
    var picture_xl: String? = null
    var tracklist: String? = null
    var type: String? = null
}

class Album: Serializable {
    var id: Int? = null
    var title: String? = null
    var cover: String? = null
    var cover_small: String? = null
    var cover_medium: String? = null
    var cover_big: String? = null
    var cover_xl: String? = null
    var tracklist: String? = null
    var type: String? = null
}