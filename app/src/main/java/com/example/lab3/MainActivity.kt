package com.example.lab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id:Int=item.itemId
        when(id)
        {
            R.id.action_input-> {
                var intent: Intent =Intent(this,InputActivity::class.java)
                startActivity(intent)
            }
//            R.id.action_change->
            R.id.action_exit->this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}