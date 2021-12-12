package com.br.week5.funwithreceiver.takeif



/**
 * O objetivo que chama a funcao takeIf passa por uma avaliacao implementada
 * pela funcao lambda passada como arumento para propria takeIf. Se o resultado da funcao
 * lambda for true, takeif retorna o objeto do contrario retorna null
 * */
private fun <T> checkTakeIf(value: T, check: (T) -> Boolean) =  value.takeIf(check)


private fun dummieSample() {
    val s = checkTakeIf(arrayOf(0, 2, 4, 8)) { array -> array.all { v -> v %  2 == 0 } }
    (s as? Array<Int>)?.let {
            array ->
        array.forEach {
            println(it)
        }
    }
}

fun main() {

}