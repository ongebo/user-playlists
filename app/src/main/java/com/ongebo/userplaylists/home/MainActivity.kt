package com.ongebo.userplaylists.home

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongebo.userplaylists.R
import com.ongebo.userplaylists.databinding.ActivityMainBinding
import com.ongebo.userplaylists.databinding.DialogUserBinding
import com.ongebo.userplaylists.db.AppDatabase
import com.ongebo.userplaylists.db.entities.User
import com.ongebo.userplaylists.utils.showToast

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val db by lazy { AppDatabase.getDatabase(this) }
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.setObserversAndGetData()
        handleAddButtonClick()
    }

    private fun MainViewModel.setObserversAndGetData() {
        usersAndPlaylists.observe(this@MainActivity) {
            binding.rvUserPlaylists.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = UsersAdapter(it)
            }
        }

        getUsersAndPlaylists(db.usersDao())
    }

    private fun handleAddButtonClick() {
        binding.fabAddUser.setOnClickListener {
            Dialog(this).apply {
                setCancelable(false)
                setTitle(R.string.label_enter_user_details)
                val dialogBinding = DialogUserBinding.inflate(layoutInflater)
                setContentView(dialogBinding.root)
                handleDialogClicks(dialogBinding)
                show()
            }
        }
    }

    private fun Dialog.handleDialogClicks(dialogBinding: DialogUserBinding) {
        dialogBinding.apply {
            btnAddUser.setOnClickListener {
                val firstName = inputFirstName.text.toString()
                val lastName = inputLastName.text.toString()
                val sex = inputSex.text.toString()
                if (firstName.isNotBlank() && lastName.isNotBlank() && sex.isNotBlank()) {
                    viewModel.addUser(
                        User(firstName, lastName, sex),
                        db.usersDao()
                    )
                    hide()
                } else {
                    this@MainActivity.showToast(getString(R.string.prompt_valid_info))
                }
            }

            btnCancel.setOnClickListener {
                hide()
            }
        }
    }
}
