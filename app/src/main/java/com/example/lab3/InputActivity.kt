package com.example.lab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton

class InputActivity : AppCompatActivity() {
    private lateinit var sizeX:EditText
    private lateinit var sizeY:EditText
    private lateinit var incsize:RadioButton
    private lateinit var decsize:RadioButton
    private lateinit var okButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        sizeX=findViewById(R.id.sizeX)
        sizeY=findViewById(R.id.sizeY)
        incsize=findViewById(R.id.incrase)
        decsize=findViewById(R.id.decrase)
        okButton=findViewById(R.id.OK)
        okButton.setOnClickListener({
            var intent:Intent=Intent()
            intent.putExtra("SIZE_X",sizeX.text.toString().toInt())
            intent.putExtra("SIZE_Y",sizeY.text.toString().toInt())
            if(incsize.isChecked)
                intent.putExtra("CHANGE",1)
            else
                intent.putExtra("CHANGE",-1)
            setResult(RESULT_OK,intent)

            finish()
        })
    }

}