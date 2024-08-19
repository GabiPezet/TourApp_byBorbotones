package com.example.testpicasso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testpicasso.databinding.ActivityTourPackageDetailsBinding
import com.example.testpicasso.repositories.UserService
import com.squareup.picasso.Picasso

class TourPackageDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTourPackageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = " TourApp - Destination details. "

        val currentPackage = UserService.getCurrentPackageDetail()

        val nameTourPackage = binding.txTituleDetails
        val detailsTourPackage = binding.tvDescriptionDetails
        val imageOne = binding.ivImageDetails1
        val imageTwo = binding.ivImageDetails2
        val imageThree = binding.ivImageDetails3

        nameTourPackage.text = currentPackage!!.destination.name
        detailsTourPackage.text = currentPackage.destination.description
        val urlLogoPrincipal1 = currentPackage.destination.pictures[0]
        Picasso.get()
            .load(urlLogoPrincipal1)
            .error(R.drawable.captura2)
            .into(imageOne)

        val urlLogoPrincipal2 = currentPackage.destination.pictures[1]
        Picasso.get()
            .load(urlLogoPrincipal2)
            .error(R.drawable.captura2)
            .into(imageTwo)

        val urlLogoPrincipal3 = currentPackage.destination.pictures[2]
        Picasso.get()
            .load(urlLogoPrincipal3)
            .error(R.drawable.captura2)
            .into(imageThree)

    }
}