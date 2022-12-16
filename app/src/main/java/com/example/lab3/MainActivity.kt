package com.example.lab3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private var size_X:Int = 0
    private var size_Y:Int = 0
    private var change:Int = 0
    private lateinit var  changeItemMenu:MenuItem
    private lateinit var view:View;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view=findViewById(R.id.figure)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        changeItemMenu= menu!!.findItem(R.id.action_change)
        changeItemMenu.isEnabled=false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id:Int=item.itemId
        when(id)
        {
            R.id.action_input-> {
                var intent: Intent =Intent(this,InputActivity::class.java)
                getResult.launch(intent)
            }
            R.id.action_change->
            {
                if(change==1){
                    view.layoutParams.width+=size_X
                    view.layoutParams.height+=size_Y
                    view.requestLayout()
                }
                else if(change==-1)
                {
                    view.layoutParams.width-=size_X
                    view.layoutParams.height-=size_Y
                    view.requestLayout()
                }
            }
            R.id.action_exit->this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
    private val getResult=
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode== Activity.RESULT_OK){
                var intent:Intent=it.data!!
                size_X=intent.getIntExtra("SIZE_X",0)
                size_Y=intent.getIntExtra("SIZE_Y",0)
                change=intent.getIntExtra("CHANGE",0)
                changeItemMenu.isEnabled=true
            }
        }

}