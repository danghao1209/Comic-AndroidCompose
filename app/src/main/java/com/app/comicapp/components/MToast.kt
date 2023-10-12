package com.app.comicapp.components

import android.content.Context
import android.widget.Toast

public fun mToast(context: Context, title: String){
    Toast.makeText(context, "$title", Toast.LENGTH_LONG).show()
}