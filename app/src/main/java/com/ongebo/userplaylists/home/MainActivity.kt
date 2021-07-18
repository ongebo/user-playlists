package com.ongebo.userplaylists.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongebo.userplaylists.databinding.ActivityMainBinding
import com.ongebo.userplaylists.db.AppDatabase

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val db by lazy { AppDatabase.getDatabase(this) }
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.setObserversAndGetData()
    }

    private fun MainViewModel.setObserversAndGetData() {
        usersAndPlaylists.observe(this@MainActivity) {
            binding.rvUserPlaylists.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = MainAdapter(it)
            }
        }

        getUsersAndPlaylists(db.usersDao())
    }
}
