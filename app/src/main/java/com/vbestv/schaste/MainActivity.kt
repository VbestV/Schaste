package com.vbestv.schaste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vbestv.schaste.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //переход на галерею помещений
        binding.mainButton.setOnClickListener{
            val intent = Intent(this,GalleryActivity::class.java)
            startActivity(intent)
        }
    }
}