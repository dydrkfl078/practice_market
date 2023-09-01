package com.example.applemarket

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class ShowDialog {
    fun showDialog(context: Context, setTitle: String, setMessage: String, completion: () -> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setTitle(setTitle)
            setMessage(setMessage)
            setIcon(R.drawable.baseline_android_24)
            setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int -> completion() }
            setNegativeButton("취소") { dialogInterface: DialogInterface, i: Int -> dialogInterface.dismiss() }
            show()
        }
    }
}