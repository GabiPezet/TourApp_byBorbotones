package com.example.testpicasso.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testpicasso.data.Purchase
import com.example.testpicasso.databinding.ItemHistoryuserBinding
import com.example.testpicasso.repositories.UserService
import com.squareup.picasso.Picasso
import com.example.testpicasso.repositories.PackageRepository


class HistoryUserAdapter(private val packages: List<Purchase>,
                         private val selectClientClickListerMakeAPurchase: (Purchase) -> Unit
) :
    RecyclerView.Adapter<HistoryUserAdapter.PackagesViewHolder>() {

    class PackagesViewHolder (val binding: ItemHistoryuserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackagesViewHolder {
        val packageBinding = ItemHistoryuserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PackagesViewHolder(packageBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PackagesViewHolder, position: Int) {
        val currentUser = UserService.getCurrentUser()!!

        val purchase = packages[position]
        val packageName = PackageRepository.getById(purchase.packageId)
        holder.binding.tvHistoryWelcomeMessage.text = "Dear ${currentUser.name}. This is your travel log: "
        holder.binding.tvHistoryPackageName.text = "Package Purchased: ${packageName.name}"
        holder.binding.tvHistoryPurchaseCode.text = "Purchase Code: ${purchase.id}"
        holder.binding.tvHistoryPurchaseDate.text = "Purchase Date: ${purchase.createdDate}"
        holder.binding.tvHistoryPurchasePrice.text = "Total Price: ${purchase.amount}"
        Picasso.get()
            .load(PackageRepository.getById(purchase.packageId).logo)
            .into(holder.binding.ivHistoryPurchaseImage)


        holder.binding.btnSelectInvoice.setOnClickListener {
            selectClientClickListerMakeAPurchase(purchase)
        }
    }

    override fun getItemCount(): Int {
        return packages.size
    }
}