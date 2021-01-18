package sample.kotlin.ext

import sample.kotlin.ext.CharExt.isReallyDigit
import kotlin.random.Random

object CharExt {
    private fun Char.isDigit() = this in '0'..'9'

    private fun Char.isReallyDigit() = Regex("\\d").matches(this.toString())

    fun comparePerformace() {
        var start = System.currentTimeMillis()
        val random = Random(start)
        val chars = ('0'..'9').toList().toTypedArray()
        val limit = 1E7.toInt()
        for (i in 0..limit) {
            chars[random.nextInt(0, 10)].isDigit()
        }
        println(System.currentTimeMillis() - start)

        start = System.currentTimeMillis()
        for (i in 0..limit) {
            chars[random.nextInt(0, 10)].isReallyDigit()
        }
        println(System.currentTimeMillis() - start)
    }
}


fun main() {
    CharExt.comparePerformace()
}