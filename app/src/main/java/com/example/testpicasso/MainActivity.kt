package com.example.testpicasso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso
import com.example.testpicasso.databinding.ActivityMainBinding
import com.example.testpicasso.repositories.UserService
import com.example.testpicasso.repositories.UserRepository


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        presentationApp(binding)
        val registerButton = binding.btnRegister
        val startButton = binding.btnStart
        val userName : Editable = binding.etNickName.text
        val userPassword: Editable = binding.etPassword.text

        registerButton.setOnClickListener {
            val registerUser = Intent(this, RegisterActivity::class.java)
            startActivity(registerUser)
        }

        startButton.setOnClickListener {
            try {
                if (userName.isNotEmpty() && userPassword.isNotEmpty()) {
                    val currentUser = UserRepository.login(userName.toString(), userPassword.toString())
                    if (currentUser != null) {
                        UserService.setCurrentUser(currentUser)
                        Toast.makeText(this, "Successful login, welcome ${currentUser.nickName} !! ", Toast.LENGTH_SHORT).show()
                        val mainMenu = Intent(this,MenuPrincipal::class.java)
                        startActivity(mainMenu)
                        userName.clear()
                        userPassword.clear()

                    }else{
                        Toast.makeText(this, "*** ERROR, wrong username or password ***", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    throw InvalidValueException()
                }
            }catch (exception : InvalidValueException){
                Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun presentationApp(binding: ActivityMainBinding) {
        val logoPrincipal = binding.ivLogoPrincipal
        val urlLogoPrincipal = R.drawable.fondo_c
        Picasso.get()
            .load(urlLogoPrincipal)
            .error(R.drawable.captura2)
            .into(logoPrincipal)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Do you want to exit the application?")
                .setPositiveButton("Yes") { _, _ ->
                    UserService.setCurrentUser(null)
                    UserService.setCurrentPackage(null)
                    finishAffinity()
                }
                .setNegativeButton("No", null)
                .show()

            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}