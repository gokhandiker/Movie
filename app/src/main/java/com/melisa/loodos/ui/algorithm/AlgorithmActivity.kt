package com.melisa.loodos.ui.algorithm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.melisa.loodos.R
import kotlinx.android.synthetic.main.activity_algorithm.*


class AlgorithmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algorithm)

        btn_change.setOnClickListener {
            var text : String = edt_input.text.toString()
            var step : String = edt_step_input.text.toString()
            analysisSentence(text,step.toInt())
        }
    }

    private fun analysisSentence(text: String?, step: Int) {
        var changedText = text
        val textLowerCase = text!!.toLowerCase()

        for (x in textLowerCase!!.indices) { // her karakter için bir döngü döndürüyoruz
            if (x == (textLowerCase!!.length - (step-1))) // endAt son karaktere geldiyse döngüyü kırıyoruz
                break

            val beginAt= x // değiştirilecek karakterlerin başlangıcı
            val endAt = x+step //  değiştirilecek karakterlerin bitişi
            val pair = textLowerCase.subSequence(x,step+x).toString() // başlangıç ve bitiş adımı arasındaki karakter grubu  -örn= "aaa"
            val char: Char = textLowerCase[x] // örnek alınan karakter -örn= 'a'
            val repeatedChar = char.toString().repeat(step) // örnek karakterin adım sayısı kadar çoğaltılmışı -örn= "aaa"

            if (pair == repeatedChar) { // eğer karakter grubuyla çoğaltılmış karakter grubu aynıysa

                if (x <= (changedText!!.length-step))
               changedText= changedText!!.replaceRange(beginAt, endAt,"*".repeat(step)) // başlangıç ve bitiş adımı arasındaki karakterleri değiştiriyoruz
            }
        }

        txt_output.text=changedText
    }


}
