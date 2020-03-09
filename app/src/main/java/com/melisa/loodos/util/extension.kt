package com.melisa.loodos.util


fun String.changeRepeatedChars(text: String?, step: Int): String {


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

    return changedText!!
}