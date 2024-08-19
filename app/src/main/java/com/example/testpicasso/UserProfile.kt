package com.example.testpicasso
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testpicasso.databinding.ActivityUserProfileBinding
import com.example.testpicasso.repositories.PurchaseRepository
import com.example.testpicasso.repositories.UserRepository
import com.example.testpicasso.repositories.UserService

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = " TourApp - User Profile"

        val userProfileName = binding.tvUserProfileName
        val userProfileSurname = binding.tvUserProfileSurname
        val userProfileAccount = binding.tvUserProfileAccount
        val userProfileImage = binding.tvImageViewUserProfile
        val userProfilePurchase = binding.tvUserProfilePurchases

        val currentUser = UserService.getCurrentUser()
        userProfileName.text = currentUser!!.name
        userProfileSurname.text = currentUser.surname
        userProfileAccount.text = currentUser.createdDate
        userProfilePurchase.text = PurchaseRepository.getPurchasesHistoryCurrenUser(currentUser).size.toString()
        val fullName = "${currentUser.name} ${currentUser.surname}"
        val photoResource = UserRepository.getPhotoResource(fullName)
        userProfileImage.setImageResource(photoResource)
    }

}