package com.ongebo.userplaylists.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ongebo.userplaylists.db.daos.UsersDao
import com.ongebo.userplaylists.db.entities.User
import com.ongebo.userplaylists.db.relationships.UserAndPlaylist
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val usersAndPlaylists = MutableLiveData<List<UserAndPlaylist>>()

    fun getUsersAndPlaylists(usersDao: UsersDao) {
        viewModelScope.launch {
            usersAndPlaylists.postValue(usersDao.getUsersAndPlaylists())
        }
    }

    fun addUser(user: User, usersDao: UsersDao) {
        viewModelScope.launch {
            usersDao.addUsers(user)

            // Update usersAndPlaylists
            usersAndPlaylists.postValue(usersDao.getUsersAndPlaylists())
        }
    }
}
