package com.vbestv.schaste


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.vbestv.schaste.databinding.ActivityGalleryBinding


class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityGalleryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        room22Insert()
        room95Insert()
        room130Insert()

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
        binding.room130.setOnClickListener {
            if (binding.tableLayout3.visibility ==View.GONE){
                binding.tableLayout3.visibility = View.VISIBLE
            }
            else{
                binding.tableLayout3.visibility = View.GONE
            }
        }

    }

    private fun room130Insert() {
        Glide.with(this)
            .load(R.drawable.room130_1)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView2)
        Glide.with(this)
            .load(R.drawable.room130_2)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView11)
        Glide.with(this)
            .load(R.drawable.room130_3)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView12)
        Glide.with(this)
            .load(R.drawable.room130_4)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView3)
        Glide.with(this)
            .load(R.drawable.room130_5)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView17)
        Glide.with(this)
            .load(R.drawable.room130_6)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView18)
        Glide.with(this)
            .load(R.drawable.room130_7)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView10)
        Glide.with(this)
            .load(R.drawable.room130_8)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView19)
        Glide.with(this)
            .load(R.drawable.room130_9)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView20)
    }

    private fun room95Insert() {
        Glide.with(this)
            .load(R.drawable.room95_1)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView21)
        Glide.with(this)
            .load(R.drawable.room95_2)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView22)
        Glide.with(this)
            .load(R.drawable.room95_3)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView23)
        Glide.with(this)
            .load(R.drawable.room95_4)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView24)
        Glide.with(this)
            .load(R.drawable.room95_5)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView25)
        Glide.with(this)
            .load(R.drawable.room95_6)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView26)
        Glide.with(this)
            .load(R.drawable.room95_7)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView27)
        Glide.with(this)
            .load(R.drawable.room95_8)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView28)
        Glide.with(this)
            .load(R.drawable.room95_9)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView29)
    }


    private fun room22Insert() {
        Glide.with(this)
            .load(R.drawable.room22_1)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView4)
        Glide.with(this)
            .load(R.drawable.room22_2)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView5)
        Glide.with(this)
            .load(R.drawable.room22_3)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView6)
        Glide.with(this)
            .load(R.drawable.room22_4)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView7)
        Glide.with(this)
            .load(R.drawable.room22_5)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView8)
        Glide.with(this)
            .load(R.drawable.room22_6)
            .centerCrop()
            .error(R.drawable.broken_image_24)
            .fallback(R.drawable.image_not_supported_24)
            .into(binding.imageView9)
    }
}