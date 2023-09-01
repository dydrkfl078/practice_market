package com.example.applemarket

import java.text.DecimalFormat

object MakeComma {
    fun makeComma(num:Int?):String{
        val formatter = DecimalFormat("###,###")
        return formatter.format(num)
    }
}