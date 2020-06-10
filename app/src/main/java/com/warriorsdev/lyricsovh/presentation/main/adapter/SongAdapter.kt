package com.warriorsdev.lyricsovh.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.warriorsdev.lyricsovh.R
import com.warriorsdev.lyricsovh.data.entities.Song
import kotlinx.android.synthetic.main.item_song.view.*
import kotlin.properties.Delegates

class SongAdapter(private val onSongClicked: (song: Song) -> Unit) :
    RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private var songList: List<Song> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_song, parent, false)
        val holder = SongViewHolder(view)

        holder.itemView.contentItem.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                onSongClicked.invoke(songList[holder.adapterPosition])
            }
        }
        return holder
    }

    override fun getItemCount(): Int = songList.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val cat: Song = songList[position]
            holder.bind(cat)
        }
    }

    fun updateData(newSongList: List<Song>) { songList = newSongList }

    class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(song: Song) {
            itemView.itemSongTitleTextView.text = song.title
            Glide.with(itemView.context)
                .load(song.album?.cover)
                .centerCrop()
                .thumbnail()
                .into(itemView.itemArtistImageView)
        }
    }
}