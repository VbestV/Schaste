package com.vbestv.schaste

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.vbestv.schaste.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Glide.with(this)
            .load(R.drawable.background)
            .centerCrop()
            .into(binding.imageView)

        binding.mainButton.setOnClickListener{
            val intent = Intent(this,GalleryActivity::class.java)
            startActivity(intent)
            onPause()
        }

        binding.mainButton1.setOnClickListener {
            val intent = Intent(this,CalcActivity::class.java)
            startActivity(intent)
            onPause()
        }

        binding.mainButton3.setOnClickListener {
            if (!binding.tableContact.isVisible)
            {
                binding.tableContact.visibility = View.VISIBLE
            }
            else
            {
                binding.tableContact.visibility = View.GONE
            }
        }

        binding.mapImg.setOnClickListener {
            showmap()
        }
        binding.mapAdress.setOnClickListener {
            showmap()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }
    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }


    private fun showmap() {
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=56.01205284934431, 92.87463471078709(Пр.Мира 49)"))
        mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}