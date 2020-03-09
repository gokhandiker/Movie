package com.melisa.loodos.ui.choose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.melisa.loodos.R
import com.melisa.loodos.ui.algorithm.AlgorithmActivity
import com.melisa.loodos.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_choose.*

class ChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        btn_recyclerview.setOnClickListener {
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }

        btn_algorithm.setOnClickListener {
            val mainIntent = Intent(this, AlgorithmActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }
}
