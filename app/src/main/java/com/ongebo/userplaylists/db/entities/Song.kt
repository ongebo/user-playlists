package com.ongebo.userplaylists.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "song_id") val songId: Int = 0,
    @ColumnInfo(name = "song_name") val songName: String
)
