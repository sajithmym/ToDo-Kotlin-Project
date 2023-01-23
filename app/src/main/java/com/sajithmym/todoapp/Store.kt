package com.sajithmym.todoapp

import android.content.Context
import android.widget.Toast
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


class Store {

    val file = "ToDo_Items.dat"

    fun write_To(Item : ArrayList<String>, context: Context){  // This Method Use for Store Data to a File

        var fos : FileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE)
        var oos = ObjectOutputStream(fos)
        oos.writeObject(Item)
        oos.close()
    }

    fun getMemory_To(context : Context) : ArrayList<String> // This Method Use for Get Data to a File
    {
        var ListItem : ArrayList<String>
        try {
            val fis : FileInputStream = context.openFileInput(file) // OpenFile
            var ois = ObjectInputStream(fis)
            ListItem = ois.readObject() as ArrayList<String>
            ois.close()
        } catch (e: Exception) {
            ListItem = ArrayList()
        }
        return ListItem
    }
}