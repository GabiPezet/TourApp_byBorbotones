package com.example.testpicasso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testpicasso.adapters.HistoryUserAdapter
import com.example.testpicasso.data.Purchase
import com.example.testpicasso.databinding.ActivityPurchaseHistoryBinding
import com.example.testpicasso.repositories.UserService
import com.example.testpicasso.repositories.PurchaseRepository

class PurchaseHistory : AppCompatActivity() {
    private lateinit var binding: ActivityPurchaseHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = " TourApp - Purchase history."
        viewHistoryPackage()
    }

    private fun viewHistoryPackage() {

        val selectClientClickListerMakeAPurchase = { purchase: Purchase ->
            Toast.makeText(this, "Invoice CodeRef.${purchase.userId}-${purchase.id} has been downloaded", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerViewHistory.adapter = HistoryUserAdapter(
            PurchaseRepository.getPurchasesHistoryCurrenUser(UserService.getCurrentUser()!!
        ), selectClientClickListerMakeAPurchase)
        binding.recyclerViewHistory.layoutManager = LinearLayoutManager(this)
    }
}