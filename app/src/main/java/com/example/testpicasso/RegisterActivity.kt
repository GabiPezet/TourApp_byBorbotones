package com.example.testpicasso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.testpicasso.databinding.ActivityRegisterBinding
import com.example.testpicasso.data.User
import com.example.testpicasso.repositories.UserRepository
import java.time.LocalDate

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = " TourApp - User Registration."

        val nickname = binding.nickname.text
        val passFirstEntry = binding.pass1.text
        val passSecondEntry = binding.pass2.text
        val name = binding.name.text
        val surname = binding.surname.text
        val backToMain = binding.back

        binding.Register.setOnClickListener {
            try {
                if (nickname.isNotEmpty() && passFirstEntry.isNotEmpty()&& passSecondEntry.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty()){
                    if(!UserRepository.containUser(nickname.toString())){
                        if (binding.pass1.text.toString() == binding.pass2.text.toString()) {
                            val id = ((Math.random() * 9999) + 1).toLong()
                            val money = 300000.0
                            val todayDate: LocalDate = LocalDate.now()
                            val newUser = User(
                                id,
                                binding.nickname.text.toString(),
                                binding.pass1.text.toString(),
                                binding.name.text.toString(),
                                binding.surname.text.toString(),
                                money,
                                todayDate.toString()
                            )

                            UserRepository.addUser(newUser)
                            Toast.makeText(this, "Successful Registration", Toast.LENGTH_LONG).show()
                            finish()
                        } else {
                            Toast.makeText(this, "Password Does Not Match", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(this, "The user already exists, please choose another username", Toast.LENGTH_LONG).show()
                    }

                } else {
                    throw InvalidValueException()
                }
            }catch (exception : InvalidValueException){
                Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()
            }

        }

        backToMain.setOnClickListener {
            goToLogin()
            finish()
        }


    }

    private fun goToLogin(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }


}