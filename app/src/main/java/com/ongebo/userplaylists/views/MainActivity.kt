package com.ongebo.userplaylists.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongebo.userplaylists.databinding.ActivityMainBinding
import com.ongebo.userplaylists.db.AppDatabase
import com.ongebo.userplaylists.view_models.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        viewModel.setObserversAndGetData()
    }

    private fun init() {
        db = AppDatabase.getDatabase(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun MainViewModel.setObserversAndGetData() {
        usersAndPlaylists.observe(this@MainActivity) {
            binding.rvUserPlaylists.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MainAdapter(it)
            }
        }

        getUsersAndPlaylists(db.usersDao())
    }
}
