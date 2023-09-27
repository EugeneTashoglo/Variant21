package com.example.lab3


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent

import android.widget.RadioGroup



class InputActivity : AppCompatActivity() {
    private lateinit var sizeX: EditText
    private lateinit var sizeY: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var okButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        sizeX = findViewById(R.id.sizeX)
        sizeY = findViewById(R.id.sizeY)
        radioGroup = findViewById(R.id.radio)
        okButton = findViewById(R.id.OK)

        okButton.setOnClickListener {
            val minHardDrive = sizeX.text.toString().toInt()
            val maxPrice = sizeY.text.toString().toInt()
            val sortBy = when (radioGroup.checkedRadioButtonId) {
                R.id.incrase -> "Hard drive"
                R.id.decrase -> "Price"
                else -> "Hard drive"
            }

            val intent = Intent()
            intent.putExtra("minHardDrive", minHardDrive)
            intent.putExtra("maxPrice", maxPrice)
            intent.putExtra("sortBy", sortBy)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}

