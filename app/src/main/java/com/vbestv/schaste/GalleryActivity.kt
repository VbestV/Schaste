package com.vbestv.schaste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.vbestv.schaste.databinding.ActivityGalleryBinding

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityGalleryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            onDestroy()
        }
        binding.gallery22.setOnClickListener{
            if (binding.room22.visibility ==View.GONE){
                binding.room22.visibility = View.VISIBLE
            }
            else{
                binding.room22.visibility = View.GONE
            }
        }

    }
}