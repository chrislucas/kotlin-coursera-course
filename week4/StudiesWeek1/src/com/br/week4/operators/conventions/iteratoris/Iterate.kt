package com.br.week4.operators.conventions.iteratoris

private fun iterateThroughString() {
    /**
     * iterator uma string  usando sintaxe forEach so eh possivel
     * por conta da extension fun
     *
     * public operator fun CharSequence.iterator(): CharIterator {}
     *
     * que transforma a String num Iterator<Char>
     * */

    for (c in "chrislucas") {
        println(c)
    }
}


private fun iterateThroughIntRange() {
    /**
     * in =override fun iterator(): IntIterator = IntProgressionIterator(first, last, step)
     * */
    for (i in 10..100) {
        println(i)
    }
}

private fun iterateThroughList() {
    /**
     * in == Collection .  override fun iterator(): Iterator<E>
     * */
    for (i in (1..100).toSet()) {
        println(i)
    }
}

private fun iterateThroughArray() {
    // Array.kt =  public operator fun iterator(): Iterator<T>
    for (i in "chris".split(" ").toTypedArray()) {
        println(i)
    }
}

fun main() {}