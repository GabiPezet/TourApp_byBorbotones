package com.example.testpicasso

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuBuilder
import com.example.testpicasso.databinding.ActivityMenuPrincipalBinding
import com.example.testpicasso.repositories.UserService
import com.squareup.picasso.Picasso
import com.example.testpicasso.repositories.PurchaseRepository


class MenuPrincipal : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        wallpaperMenuPrincipal(binding)
        setContentView(binding.root)
        val currentUser = UserService.getCurrentUser()
        supportActionBar?.title = " Welcome ${ currentUser!!.name} ${ currentUser.surname}"

        val viewAccountMoney = binding.btnAccountMoney
        val selectAndListTourPackage = binding.btnSelectTourPack
        val viewPurchaseHistory = binding.btnHistory
        val logOut = binding.btnLogOut
        val textMenuPrincipal = binding.tvMenuPrincipal

        textMenuPrincipal.text = "Main Menu"


        selectAndListTourPackage.setOnClickListener{
            val intent = Intent(this,TourPackageList::class.java)
            startActivity(intent)
        }

        viewPurchaseHistory.setOnClickListener {
            if ((PurchaseRepository.getPurchasesHistoryCurrenUser(UserService.getCurrentUser()!!)).isNotEmpty()) {
                val intent = Intent(this, PurchaseHistory::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"It cannot be displayed because your history is empty.",Toast.LENGTH_SHORT).show()
            }
        }

        viewAccountMoney.setOnClickListener{
            val intent = Intent(this, ShowMoneyCurrentUser::class.java)
            startActivity(intent)
        }

        logOut.setOnClickListener{
            showExitConfirmationDialog()

        }


    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        if (menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.userProfile -> {
                val intent = Intent(this, UserProfile::class.java)
                startActivity(intent)
                return true }
            R.id.userMoney -> {
                val intent = Intent(this, ShowMoneyCurrentUser::class.java)
                startActivity(intent)
                return true }
            R.id.purchaseHistoryUser -> {
                if ((PurchaseRepository.getPurchasesHistoryCurrenUser(UserService.getCurrentUser()!!)).isNotEmpty()) {
                    val intent = Intent(this, PurchaseHistory::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"It cannot be displayed because your history is empty.",Toast.LENGTH_SHORT).show()
                }
                return true }
            R.id.aboutUs -> {
                val intent = Intent(this, TheBorbotonesAboutUs::class.java)
                startActivity(intent)
                return true }
            R.id.action_bar_logOut -> {
                showExitConfirmationDialog()
                return true }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you want to log out and return to the start menu?")
            .setPositiveButton("Yes") { _, _ ->
                UserService.setCurrentUser(null)
                UserService.setCurrentPackage(null)
                Toast.makeText(this,"Successful logout.",Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun wallpaperMenuPrincipal(binding: ActivityMenuPrincipalBinding){
        val wallpaperMenu = binding.ivWallpaperFondo
        val urlWallpaperMenu = R.drawable.fondo_c
        Picasso.get()
            .load(urlWallpaperMenu)
            .error(R.drawable.fondo_a)
            .into(wallpaperMenu)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Do you want to log out and return to the start menu?")
                .setPositiveButton("Yes") { _, _ ->
                    UserService.setCurrentUser(null)
                    UserService.setCurrentPackage(null)
                    Toast.makeText(this,"Successful logout.",Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton("No", null)
                .show()

            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}