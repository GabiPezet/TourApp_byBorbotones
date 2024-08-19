package com.example.testpicasso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testpicasso.adapters.TourPackageAdapter
import com.example.testpicasso.data.TourPackage
import com.example.testpicasso.databinding.ActivityTourPackageListBinding
import com.example.testpicasso.repositories.PackageRepository

class TourPackageList : AppCompatActivity() {
    private lateinit var binding: ActivityTourPackageListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTourPackageListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = " TourApp - TourPackageList. "
        selectAndListTourPackage()
    }

    private fun selectAndListTourPackage() {

        val selectClientClickListerMakeAPurchase = {
            finish()
        }

        val selectClickOnTourPackageDetails = { _: TourPackage ->
            val intent = Intent(this,TourPackageDetails::class.java)
            startActivity(intent)
        }

        val selectClientClickListerBuyAPurchase = { _: TourPackage ->
            val intent = Intent(this,PurchasePackage::class.java)
            startActivity(intent)
        }

        binding.recyclerViewClients.adapter = TourPackageAdapter(PackageRepository.getPackagesList(), selectClientClickListerMakeAPurchase , selectClientClickListerBuyAPurchase , selectClickOnTourPackageDetails)
        binding.recyclerViewClients.layoutManager = LinearLayoutManager(this)
    }


}