package com.example.testpicasso.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testpicasso.databinding.ItemTourpackageBinding


import com.squareup.picasso.Picasso
import com.example.testpicasso.data.TourPackage
import com.example.testpicasso.repositories.UserService

class TourPackageAdapter(
    private val packages: List<TourPackage>,
    private val selectClientClickListerMakeAPurchase: () -> Unit,
    private val selectClientClickListerBuyAPurchase: (TourPackage) -> Unit,
    private val selectClickOnTourPackageDetails: (TourPackage) -> Unit
) :
    RecyclerView.Adapter<TourPackageAdapter.PackagesViewHolder>() {

    class PackagesViewHolder (val binding: ItemTourpackageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackagesViewHolder {
        val packageBinding = ItemTourpackageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PackagesViewHolder(packageBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PackagesViewHolder, position: Int) {

        val tourPackage = packages[position]
        holder.binding.txtPackageName.text = tourPackage.destination.name
        holder.binding.tvName.text = "Package: ${tourPackage.name}"
        holder.binding.tvPrice.text = "Price: ${tourPackage.price}"
        holder.binding.ratingBar.rating = tourPackage.stars.toFloat()
        holder.binding.tvDuration.text = "Duration: ${tourPackage.duration} hours"
        holder.binding.tvTransportType.text = "Transport: ${tourPackage.transport}"
        Picasso.get()
            .load(tourPackage.logo)
            .into(holder.binding.ivLogoPackage)



        holder.binding.btnDetailsTourPackage.setOnClickListener {
            selectClickOnTourPackageDetails(tourPackage)
            UserService.setCurrentPackageDetail(tourPackage)

        }



        holder.binding.btnReturn.setOnClickListener {
            selectClientClickListerMakeAPurchase()
        }

        holder.binding.btnBuyPackage.setOnClickListener {
            selectClientClickListerBuyAPurchase(tourPackage)
            UserService.setCurrentPackage(tourPackage)

        }
    }

    override fun getItemCount(): Int {
        return packages.size
    }
}