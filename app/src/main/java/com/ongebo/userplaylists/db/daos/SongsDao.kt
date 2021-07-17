package com.ongebo.userplaylists.db.daos

import androidx.room.*
import com.ongebo.userplaylists.db.entities.Song

@Dao
interface SongsDao {

    @Insert
    suspend fun addSong(song: Song)

    @Update
    suspend fun updateSong(song: Song)

    @Delete
    suspend fun deleteSong(song: Song)

    @Query("SELECT * FROM songs")
    suspend fun getSongs()

    @Query("SELECT * FROM songs WHERE id = :songId")
    suspend fun getSong(songId: Int)
}
