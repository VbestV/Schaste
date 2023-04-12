package com.vbestv.schaste

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.vbestv.schaste.databinding.ActivityGalleryBinding
import java.io.File

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityGalleryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        room22Insert()

        binding.backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        binding.gallery22.setOnClickListener{
            if (binding.tableLayout.visibility ==View.GONE){
                binding.tableLayout.visibility = View.VISIBLE
            }
            else{
                binding.tableLayout.visibility = View.GONE
            }
        }
        binding.room95.setOnClickListener{
            if (binding.tableLayout2.visibility ==View.GONE){
                binding.tableLayout2.visibility = View.VISIBLE
            }
            else{
                binding.tableLayout2.visibility = View.GONE
            }
        }

    }

    @SuppressLint("CheckResult")
    private fun room22Insert() {
        Glide.with(this)
            .load(R.drawable.room22_1)
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView4)
        Glide.with(this)
            .load(R.drawable.room22_2)
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView5)
        Glide.with(this)
            .load(R.drawable.room22_3)
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView6)
        Glide.with(this)
            .load(R.drawable.room22_4)
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView7)
        Glide.with(this)
            .load(R.drawable.room22_5)
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView8)
        Glide.with(this)
            .load(R.drawable.room22_6)
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView9)
    }
}