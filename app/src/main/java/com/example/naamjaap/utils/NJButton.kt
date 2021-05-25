package com.example.naamjaap.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class NJButton(context: Context, attrs: AttributeSet) : AppCompatButton(context, attrs) {

    init {

        // call the function to apply the font to the components
        applyFont()
    }

    private fun applyFont() {

        //this is use to get the file from the assets folder and set it to the text view
        val typeface : Typeface = Typeface.createFromAsset(context.assets, "Montserrat-Bold.ttf")
        setTypeface(typeface)
    }
}