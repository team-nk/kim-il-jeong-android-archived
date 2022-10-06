package com.teamnk.kimiljung.util

import android.content.Context
import android.widget.Toast

fun print(context: Context, message : String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}