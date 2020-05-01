package com.custom.textinputlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.custom.textinputlayout.custom.component.INFO_STATE
import kotlinx.android.synthetic.main.activity_main.*

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
}
