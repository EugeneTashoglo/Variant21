package com.example.lab3


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

data class Computer(val type: String, val price: Int, val hardDrive: Int)

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private val computers = mutableListOf<Computer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        val inputStream = assets.open("computer.txt")
        inputStream.bufferedReader().forEachLine { line ->
            val parts = line.split(" ")
            computers.add(Computer(parts[0], parts[1].toInt(), parts[2].toInt()))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.chose -> {
                val intent = Intent(this, InputActivity::class.java)
                startActivityForResult(intent, 1)
            }
            R.id.action_change -> {
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    computers.map { "${it.type} ${it.price} ${it.hardDrive}" })
                listView.adapter = adapter
            }
            R.id.action_exit -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val minHardDrive = data?.getIntExtra("minHardDrive", 0) ?: 0
            val maxPrice = data?.getIntExtra("maxPrice", Int.MAX_VALUE) ?: Int.MAX_VALUE
            val sortBy = data?.getStringExtra("sortBy") ?: "Hard drive"

            val filteredComputers = computers.filter { it.hardDrive >= minHardDrive && it.price <= maxPrice }
            val sortedComputers = when (sortBy) {
                "Hard drive" -> filteredComputers.sortedBy { it.hardDrive }
                "Price" -> filteredComputers.sortedBy { it.price }
                else -> filteredComputers
            }

            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                sortedComputers.map { "${it.type} ${it.price} ${it.hardDrive}" })
            listView.adapter = adapter
        }

    }

}




