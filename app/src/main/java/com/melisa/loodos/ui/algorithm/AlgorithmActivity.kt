package com.melisa.loodos.ui.algorithm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.melisa.loodos.R
import com.melisa.loodos.util.changeRepeatedChars
import kotlinx.android.synthetic.main.activity_algorithm.*


class AlgorithmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algorithm)

        btn_change.setOnClickListener {
            var text : String = edt_input.text.toString()
            var step : String = edt_step_input.text.toString()

            txt_output.text=text.changeRepeatedChars(text,step.toInt())

        }
    }
}
