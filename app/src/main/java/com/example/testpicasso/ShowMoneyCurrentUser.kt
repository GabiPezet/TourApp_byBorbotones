package com.example.testpicasso

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testpicasso.databinding.ActivityShowMoneyCurrentUserBinding
import com.example.testpicasso.repositories.UserService

class ShowMoneyCurrentUser : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShowMoneyCurrentUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = " TourApp - User money. "

        val userMoney = binding.tvUserMoney
        val currentUser = UserService.getCurrentUser()
        val money = binding.etCurrentMoneyUser
        money.setText(currentUser!!.money.toString())

        userMoney.text = "Welcome ${currentUser.name} ${currentUser.surname}\n\nThis is your current account balance: "
    }
}