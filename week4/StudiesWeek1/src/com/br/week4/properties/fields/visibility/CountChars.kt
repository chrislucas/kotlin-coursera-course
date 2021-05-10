package com.br.week4.properties.fields.visibility

class CountChars {
    // propriedade somente de LEITURA
    var counter = 0
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}


fun main() {
    val counter = CountChars()
    counter.addWord("chris")
    counter.addWord("lucas")
    println(counter.counter)
}