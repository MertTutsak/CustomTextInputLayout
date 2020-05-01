package com.custom.textinputlayout

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.custom.textinputlayout.custom.component.CustomTextInputlayout
import com.custom.textinputlayout.custom.component.INFO_STATE
import com.custom.textinputlayout.extension.notNull
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.component_textinputlayout.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.textInputLayout.setEndIconClickListener {
            this.textInputLayout.clear()
        }


        this.textInputLayout.onTextChanged {
            this.textInputLayout.setEndIconVisible(!it.isNullOrEmpty())

            if (!it.isNullOrEmpty()) {
                this.textInputLayout.setInfoState(INFO_STATE.DEFAULT)
            } else {
                this.textInputLayout.setInfoState(INFO_STATE.ERROR)
            }
        }
    }

    /*for (i in 0..Int.MAX_VALUE){
        this.textInputLayout.edtInput.findViewById<TextView>(i).notNull {
            Log.i("FINDBYID",it.toString())
        }
    }*/

}
