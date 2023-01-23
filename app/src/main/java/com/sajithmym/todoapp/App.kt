package com.sajithmym.todoapp

import android.app.backup.FileBackupHelper
import android.content.DialogInterface
import android.icu.text.Transliterator.Position
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.logging.FileHandler

class App : AppCompatActivity() {
    //Define Variables
    lateinit var Item : EditText
    lateinit var Add_Button : Button
    lateinit var full_list : ListView

    var ToDo_List = ArrayList<String>() // Create a Arraylist For Store ToDo Items
    var Work_With_File = Store() // Create an Object for use Store.kt Class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        // Assign To Varibale Using Id
        Item = findViewById(R.id.item)
        Add_Button = findViewById(R.id.add)
        full_list = findViewById(R.id.list)

        ToDo_List = Work_With_File.getMemory_To(this) // Call getMemory_To method from Store.kt class

        var Arr_Adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1,ToDo_List)
        full_list.adapter = Arr_Adapter // Perform operations in ListView of our app

        Add_Button.setOnClickListener {
            var ToDo_Work_Name = Item.text.toString()
            if (ToDo_Work_Name != "") { // Check  TextField Empty or not
                ToDo_List.add(ToDo_Work_Name)
                Item.setText("")
                Work_With_File.write_To(ToDo_List, applicationContext)
                Arr_Adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(applicationContext,"No Work Found to Add...",Toast.LENGTH_SHORT).show()
            }
        }

        full_list.setOnItemClickListener { adapterView, view, position, id ->
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage(" Do You wont to Delete ? ")
            alert.setCancelable(false)
            
            alert.setNegativeButton("Cencel", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })

            alert.setPositiveButton("Sure", DialogInterface.OnClickListener { dialog, which ->
                ToDo_List.removeAt(position)
                Arr_Adapter.notifyDataSetChanged()
                Work_With_File.write_To(ToDo_List,applicationContext)
            })

            alert.create().show()
        }
    }
}