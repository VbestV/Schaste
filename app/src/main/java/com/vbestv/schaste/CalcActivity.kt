package com.vbestv.schaste

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.vbestv.schaste.databinding.ActivityCalcBinding



class CalcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalcBinding
    var numday = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Glide.with(this)
            .load(R.drawable.background)
            .centerCrop().into(binding.imageView)
        binding.backButton.setOnClickListener{
            finish()
        }

        val items = listOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница","Суббота","Воскресенье")
        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spinner.adapter = adapter
        spinner.setSelection(0)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
                numday = i
                updateNum(numday)
            }
            override fun onNothingSelected(adapterView: AdapterView<*>) {
                // Nothing to do
            }
        }
        binding.radio22.setOnClickListener {
            updateNum(numday)
        }
        binding.radio95.setOnClickListener {
            updateNum(numday)
        }
        binding.radio130.setOnClickListener {
            updateNum(numday)
        }
        binding.button.setOnClickListener {
            val chekbox = listOf(binding.checkBox2,binding.checkBox3,binding.checkBox4,binding.checkBox5,binding.checkBox6,binding.checkBox7,
                binding.checkBox8, binding.checkBox9,binding.checkBox10,binding.checkBox11,binding.checkBox12)
            val cheknum = chekbox.filter { it.isChecked }.size

            if (binding.radio22.isChecked){
                binding.textView2.text = (800 * cheknum).toString() + " руб."
            }
            else {
                if (cheknum==1) binding.textView2.text = "1800 руб."
                else binding.textView2.text = ((1800*cheknum) - cheknum * 90).toString() + " руб."
            }
        }
    }

    private fun updateNum(i:Int){
        binding.textView2.text = "0 руб."
        when(i){ 0 -> montab() 1 -> tuetab() 2 -> wedtab() 3 -> thurtab() 4 -> fritab() 5 -> sattab() else -> suntab() }
    }
    private fun restTab(){
        binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, com.google.android.material.R.drawable.abc_btn_check_material_anim)
        binding.checkBox2.isClickable = true
        binding.checkBox2.isChecked = false
        binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, com.google.android.material.R.drawable.abc_btn_check_material_anim)
        binding.checkBox3.isClickable = true
        binding.checkBox3.isChecked = false
        binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, com.google.android.material.R.drawable.abc_btn_check_material_anim)
        binding.checkBox4.isClickable = true
        binding.checkBox4.isChecked = false
        binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, com.google.android.material.R.drawable.abc_btn_check_material_anim)
        binding.checkBox5.isClickable = true
        binding.checkBox5.isChecked = false
        binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, com.google.android.material.R.drawable.abc_btn_check_material_anim)
        binding.checkBox6.isClickable = true
        binding.checkBox6.isChecked = false
        binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, com.google.android.material.R.drawable.abc_btn_check_material_anim)
        binding.checkBox7.isClickable = true
        binding.checkBox7.isChecked = false
        binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, com.google.android.material.R.drawable.abc_btn_check_material_anim)
        binding.checkBox8.isClickable = true
        binding.checkBox8.isChecked = false
        binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, com.google.android.material.R.drawable.abc_btn_check_material_anim)
        binding.checkBox9.isClickable = true
        binding.checkBox9.isChecked = false
        binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, com.google.android.material.R.drawable.abc_btn_check_material_anim)
        binding.checkBox10.isClickable = true
        binding.checkBox10.isChecked = false
        binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, com.google.android.material.R.drawable.abc_btn_check_material_anim)
        binding.checkBox11.isClickable = true
        binding.checkBox11.isChecked = false
        binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, com.google.android.material.R.drawable.abc_btn_check_material_anim)
        binding.checkBox12.isClickable = true
        binding.checkBox12.isChecked = false
    }

    private fun firedata(r: String, d: String, t: String, callback: (Boolean) -> Unit) {
        val temp = FirebaseDatabase.getInstance().getReference("1HFC9E13AL9RX1-zaLSypZxdRn51j050KqsL9GL4B0YE/$r/$d/$t")
        temp.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value.toString()
                callback(value == "да" || value == "Да")
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    private fun suntab() {
        restTab()
        if (binding.radio22.isChecked){
            firedata("22","Воскресенье","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("22","Воскресенье","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("22","Воскресенье","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("22","Воскресенье","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("22","Воскресенье","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("22","Воскресенье","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("22","Воскресенье","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("22","Воскресенье","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("22","Воскресенье","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("22","Воскресенье","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("22","Воскресенье","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio95.isChecked){
            firedata("95","Воскресенье","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("95","Воскресенье","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("95","Воскресенье","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("95","Воскресенье","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("95","Воскресенье","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("95","Воскресенье","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("95","Воскресенье","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("95","Воскресенье","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("95","Воскресенье","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("95","Воскресенье","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("95","Воскресенье","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio130.isChecked){
            firedata("130","Воскресенье","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("130","Воскресенье","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("130","Воскресенье","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("130","Воскресенье","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("130","Воскресенье","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("130","Воскресенье","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("130","Воскресенье","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("130","Воскресенье","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("130","Воскресенье","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("130","Воскресенье","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("130","Воскресенье","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
    }

    private fun sattab() {
        restTab()
        if (binding.radio22.isChecked){
            firedata("22","Суббота","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("22","Суббота","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("22","Суббота","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("22","Суббота","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("22","Суббота","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("22","Суббота","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("22","Суббота","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("22","Суббота","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("22","Суббота","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("22","Суббота","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("22","Суббота","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio95.isChecked){
            firedata("95","Суббота","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("95","Суббота","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("95","Суббота","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("95","Суббота","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("95","Суббота","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("95","Суббота","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("95","Суббота","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("95","Суббота","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("95","Суббота","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("95","Суббота","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("95","Суббота","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio130.isChecked){
            firedata("130","Суббота","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("130","Суббота","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("130","Суббота","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("130","Суббота","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("130","Суббота","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("130","Суббота","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("130","Суббота","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("130","Суббота","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("130","Суббота","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("130","Суббота","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("130","Суббота","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
    }

    private fun fritab() {
        restTab()
        if (binding.radio22.isChecked){
            firedata("22","Пятница","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("22","Пятница","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("22","Пятница","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("22","Пятница","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("22","Пятница","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("22","Пятница","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("22","Пятница","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("22","Пятница","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("22","Пятница","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("22","Пятница","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("22","Пятница","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio95.isChecked){
            firedata("95","Пятница","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("95","Пятница","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("95","Пятница","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("95","Пятница","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("95","Пятница","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("95","Пятница","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("95","Пятница","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("95","Пятница","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("95","Пятница","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("95","Пятница","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("95","Пятница","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio130.isChecked){
            firedata("130","Пятница","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("130","Пятница","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("130","Пятница","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("130","Пятница","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("130","Пятница","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("130","Пятница","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("130","Пятница","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("130","Пятница","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("130","Пятница","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("130","Пятница","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("130","Пятница","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
    }

    private fun thurtab() {
        restTab()
        if (binding.radio22.isChecked){
            firedata("22","Четверг","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("22","Четверг","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("22","Четверг","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("22","Четверг","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("22","Четверг","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("22","Четверг","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("22","Четверг","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("22","Четверг","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("22","Четверг","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("22","Четверг","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("22","Четверг","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio95.isChecked){
            firedata("95","Четверг","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("95","Четверг","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("95","Четверг","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("95","Четверг","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("95","Четверг","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("95","Четверг","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("95","Четверг","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("95","Четверг","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("95","Четверг","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("95","Четверг","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("95","Четверг","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio130.isChecked){
            firedata("130","Четверг","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("130","Четверг","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("130","Четверг","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("130","Четверг","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("130","Четверг","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("130","Четверг","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("130","Четверг","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("130","Четверг","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("130","Четверг","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("130","Четверг","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("130","Четверг","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
    }

    private fun wedtab() {
        restTab()
        if (binding.radio22.isChecked){
            firedata("22","Среда","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("22","Среда","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("22","Среда","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("22","Среда","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("22","Среда","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("22","Среда","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("22","Среда","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("22","Среда","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("22","Среда","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("22","Среда","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("22","Среда","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio95.isChecked){
            firedata("95","Среда","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("95","Среда","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("95","Среда","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("95","Среда","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("95","Среда","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("95","Среда","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("95","Среда","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("95","Среда","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("95","Среда","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("95","Среда","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("95","Среда","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio130.isChecked){
            firedata("130","Среда","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("130","Среда","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("130","Среда","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("130","Среда","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("130","Среда","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("130","Среда","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("130","Среда","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("130","Среда","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("130","Среда","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("130","Среда","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("130","Среда","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
    }

    private fun tuetab() {
        restTab()
        if (binding.radio22.isChecked){
            firedata("22","Вторник","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("22","Вторник","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("22","Вторник","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("22","Вторник","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("22","Вторник","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("22","Вторник","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("22","Вторник","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("22","Вторник","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("22","Вторник","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("22","Вторник","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("22","Вторник","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio95.isChecked){
            firedata("95","Вторник","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("95","Вторник","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("95","Вторник","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("95","Вторник","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("95","Вторник","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("95","Вторник","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("95","Вторник","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("95","Вторник","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("95","Вторник","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("95","Вторник","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("95","Вторник","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio130.isChecked){
            firedata("130","Вторник","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("130","Вторник","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("130","Вторник","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("130","Вторник","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("130","Вторник","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("130","Вторник","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("130","Вторник","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("130","Вторник","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("130","Вторник","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("130","Вторник","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("130","Вторник","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
    }

    private fun montab() {
        restTab()
        if (binding.radio22.isChecked){
            firedata("22","Понедельник","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("22","Понедельник","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("22","Понедельник","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("22","Понедельник","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("22","Понедельник","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("22","Понедельник","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("22","Понедельник","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("22","Понедельник","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("22","Понедельник","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("22","Понедельник","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("22","Понедельник","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio95.isChecked){
            firedata("95","Понедельник","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("95","Понедельник","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("95","Понедельник","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("95","Понедельник","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("95","Понедельник","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("95","Понедельник","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("95","Понедельник","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("95","Понедельник","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("95","Понедельник","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("95","Понедельник","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("95","Понедельник","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
        if (binding.radio130.isChecked){
            firedata("130","Понедельник","9-00"){ value ->
                if (value){
                    binding.checkBox2.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox2.isClickable = false
                }
            }
            firedata("130","Понедельник","10-00"){ value ->
                if (value){
                    binding.checkBox3.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox3.isClickable = false
                }
            }
            firedata("130","Понедельник","11-00"){ value ->
                if (value){
                    binding.checkBox4.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox4.isClickable = false
                }
            }
            firedata("130","Понедельник","12-00"){ value ->
                if (value){
                    binding.checkBox5.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox5.isClickable = false
                }
            }
            firedata("130","Понедельник","13-00"){ value ->
                if (value){
                    binding.checkBox6.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox6.isClickable = false
                }
            }
            firedata("130","Понедельник","14-00"){ value ->
                if (value){
                    binding.checkBox7.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox7.isClickable = false
                }
            }
            firedata("130","Понедельник","15-00"){ value ->
                if (value){
                    binding.checkBox8.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox8.isClickable = false
                }
            }
            firedata("130","Понедельник","16-00"){ value ->
                if (value){
                    binding.checkBox9.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox9.isClickable = false
                }
            }
            firedata("130","Понедельник","17-00"){ value ->
                if (value){
                    binding.checkBox10.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox10.isClickable = false
                }
            }
            firedata("130","Понедельник","18-00"){ value ->
                if (value){
                    binding.checkBox11.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox11.isClickable = false
                }
            }
            firedata("130","Понедельник","19-00"){ value ->
                if (value){
                    binding.checkBox12.buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                    binding.checkBox12.isClickable = false
                }
            }
        }
    }
}