package com.ongebo.userplaylists.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlists")
data class Playlist(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "playlist_name") val playlistName: String,
    @ColumnInfo(name = "user_id") val userId: Int,
)
