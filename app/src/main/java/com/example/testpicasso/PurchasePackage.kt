package com.example.testpicasso

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.testpicasso.data.Purchase
import com.example.testpicasso.data.TourPackage
import com.example.testpicasso.data.User
import com.example.testpicasso.databinding.ActivityPurchasePackageBinding
import com.example.testpicasso.repositories.UserService
import com.example.testpicasso.repositories.PurchaseRepository
import com.example.testpicasso.repositories.UserRepository
import java.time.LocalDate

class PurchasePackage : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPurchasePackageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = " TourApp - Purchase confirmation."
        val currentPackage = UserService.getCurrentPackage()
        val currentUser = UserService.getCurrentUser()
        val totalAmountToPay: Double
        val commission: Double
        val pair = UserService.calculateAmountToPayForThePackage(currentPackage!!)
        totalAmountToPay = pair.first
        commission = pair.second

        val packageName = binding.tvPackageNamePurchase
        val packagePrice = binding.tvCostPurchase
        val packageCommission = binding.tvCommissionPurchase
        val packageTotal = binding.tvTotalPurchase
        val confirm = binding.btnConfirm
        val cancel = binding.btnCancel

        packageName.text =
            "Package Name: ${currentPackage.name}"
        packagePrice.text =
            "Cost of your selected package is:\n $${currentPackage.price} "
        packageCommission.text =
            "Percentage of fees and taxes added:\n $commission %"
        packageTotal.text =
            "Total price:\n $$totalAmountToPay"

        cancel.setOnClickListener {
            UserService.setCurrentPackage(null)
            Toast.makeText(this,"Your purchase has been cancelled.",Toast.LENGTH_LONG).show()
            finish()
        }

        confirm.setOnClickListener {
            if (currentUser!!.money >= totalAmountToPay) {
                val newPurchase: Purchase =
                    buildNewPurchase(currentPackage, currentUser, totalAmountToPay)
                PurchaseRepository.addNewPurchase(newPurchase)
                UserRepository.modifyMoney(currentUser, totalAmountToPay)
                UserService.setCurrentPackage(null)
                Toast.makeText(this,"Successful purchase!\nHave a nice trip",Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this,"Not enough money. Select another package",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun buildNewPurchase(
        currentPackage: TourPackage,
        currentUser: User?,
        totalAmountToPay: Double
    ): Purchase {
        val lastPurchase = PurchaseRepository.getPurchaseslist().last()
        val id = lastPurchase.id + 1
        val purchase: Purchase
        val userId = currentUser!!.id
        val packageId = currentPackage.id
        val createdDate = LocalDate.now().toString()

        purchase = Purchase(id, userId, packageId, totalAmountToPay, createdDate)

        return purchase
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Do you want to cancel the purchase?")
                .setPositiveButton("Yes") { _, _ ->
                    UserService.setCurrentPackage(null)
                    finish()
                }
                .setNegativeButton("No", null)
                .show()

            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}