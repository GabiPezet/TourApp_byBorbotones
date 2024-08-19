package com.example.testpicasso

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testpicasso.databinding.ActivityTheBorbotonesAboutUsBinding

class TheBorbotonesAboutUs : AppCompatActivity() {
    private lateinit var sound: MediaPlayer
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTheBorbotonesAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = " TourApp - The borbotones"

        val aboutUsTitulo = binding.tvAboutUsTitle
        val appVersion = binding.tvInfoVersion
        val poweredBy = binding.tvPoweredby
        val aboutUsMini = binding.textView2
        sound = MediaPlayer.create(this, R.raw.besharpsbabyonboard)
        sound.start()


        aboutUsTitulo.text = "About TourApp"
        appVersion.text =
            "App Version 3.3.1 | Patch 1\nBuild Number: 20231128.331\nBuild on November 29, 2023\n" +
                    "Development Staff:\nBarzabal Cristian\nMenicucci Giuliana \nRodríguez Sofia \nPezet Gabriel"

        poweredBy.text = "Conceived and Designed By Los Borbotones\nDeveloped By Los Borbotones\n" +
                "Powered By Android Studio Giraffe | Teaching Staff of Mobile Programming I\nCopyright © 2022–2023 Google"

        aboutUsMini.text = "Version 3.3.1 TourApp UNLAM ABOUT US"


    }

    override fun onDestroy() {
        super.onDestroy()
        sound.stop()
        sound.release()


    }
}