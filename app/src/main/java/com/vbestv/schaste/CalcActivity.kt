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
    
    private fun restTab() {
    val checkBoxes = listOf(
        binding.checkBox2, binding.checkBox3, binding.checkBox4, binding.checkBox5,
        binding.checkBox6, binding.checkBox7, binding.checkBox8, binding.checkBox9,
        binding.checkBox10, binding.checkBox11, binding.checkBox12
    )

    val defaultDrawable = ContextCompat.getDrawable(binding.root.context, com.google.android.material.R.drawable.abc_btn_check_material_anim)

    checkBoxes.forEach { checkBox ->
        checkBox.buttonDrawable = defaultDrawable
        checkBox.isClickable = true
        checkBox.isChecked = false
    }
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
    
    private fun setTab(day: String) {
    val timeSlots = listOf("9-00", "10-00", "11-00", "12-00", "13-00", "14-00", "15-00", "16-00", "17-00", "18-00", "19-00")

    restTab()

    val selectedRadioId = when {
        binding.radio22.isChecked -> "22"
        binding.radio95.isChecked -> "95"
        binding.radio130.isChecked -> "130"
        else -> return // Не выбран ни один из вариантов
    }

    val checkBoxes = listOf(
        binding.checkBox2, binding.checkBox3, binding.checkBox4, binding.checkBox5,
        binding.checkBox6, binding.checkBox7, binding.checkBox8, binding.checkBox9,
        binding.checkBox10, binding.checkBox11, binding.checkBox12
    )

    for ((index, timeSlot) in timeSlots.withIndex()) {
        firedata(selectedRadioId, day, timeSlot) { value ->
            if (value) {
                checkBoxes[index].buttonDrawable = ContextCompat.getDrawable(binding.root.context, R.drawable.checkbox_indeterminate)
                checkBoxes[index].isClickable = false
            }
        }
    }
}

private fun suntab() {
    setTab("Воскресенье")
}

private fun sattab() {
    setTab("Суббота")
}

private fun fritab() {
    setTab("Пятница")
}

private fun thurtab() {
    setTab("Четверг")
}

private fun wedtab() {
    setTab("Среда")
}

private fun tuetab() {
    setTab("Вторник")
}

private fun montab() {
    setTab("Понедельник")
}

    
    
    }
}
