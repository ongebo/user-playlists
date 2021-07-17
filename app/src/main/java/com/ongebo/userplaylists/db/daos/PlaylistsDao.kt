package com.ongebo.userplaylists.db.daos

import androidx.room.*
import com.ongebo.userplaylists.db.entities.Playlist
import com.ongebo.userplaylists.db.relationships.PlaylistAndSong

@Dao
interface PlaylistsDao {

    @Insert
    suspend fun addPlaylist(playlist: Playlist)

    @Update
    suspend fun updatePlaylist(playlist: Playlist)

    @Delete
    suspend fun deletePlaylist(playlist: Playlist)

    @Query("SELECT * FROM playlists")
    suspend fun getPlaylists(): List<Playlist>

    @Query("SELECT * FROM playlists WHERE id = :playlistId")
    suspend fun getPlaylist(playlistId: Int): Playlist

    @Query("SELECT * FROM playlists")
    suspend fun getPlaylistsAndSongs(): List<PlaylistAndSong>

    @Query("SELECT * FROM playlists WHERE id = :playlistId")
    suspend fun getPlaylistAndSongs(playlistId: Int): PlaylistAndSong
}
