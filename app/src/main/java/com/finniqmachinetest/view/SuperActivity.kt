package com.finniqmachinetest.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class SuperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun updateToolbarWithoutBackButton(toolbartext: String?) {
        if (supportActionBar != null) {
            supportActionBar!!.title = toolbartext
        }
    }
    public fun sucessMessage( title : String,message : String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)


        builder.setPositiveButton(android.R.string.yes) { dialog, which ->

        }


        builder.show()
    }
}