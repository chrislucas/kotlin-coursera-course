package com.br.week4.conventions.playground

/*
    in == operator fun <T:> T.iterator(): T:Iterator
 */


private fun simpleSyntaxForIterate() {
    /*
        in
        public operator fun CharSequence.iterator(): CharIterator = object : CharIterator()
     */
    for (i in "abcdefgh")
        println(i)

    /*
        in
        open class IntProgression {
            override fun iterator(): IntIterator = IntProgressionIterator(first, last, step)
        }
     */
    for (i in 0 .. 9)
        println(i)
}


fun main() {
    simpleSyntaxForIterate()
}