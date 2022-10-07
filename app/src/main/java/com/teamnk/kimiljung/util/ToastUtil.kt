package com.teamnk.kimiljung.util

import android.content.Context
import android.widget.Toast
// TODO use snackBar instead of Toast
fun print(context: Context, message : String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun errorPrint(context: Context){
    Toast.makeText(context, "서버 통신 에러", Toast.LENGTH_SHORT).show()
}
