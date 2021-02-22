package com.br.playground3.nullable

// SafeCast "as?"
fun safeCast(any: Any?) {

    if (any is String) {
        /**
         * val _any = any as String nao eh necessairo pois o kotlin tem um sistema de smart cast
         * */
        val _any = any.toUpperCase()
        println(_any)
    }
    else if (any is String?) {
        println("Nullable String")
    }



}

fun <T> simpleSafeCast(any: Any?, orElse: () -> T) {
    // safe cast
    /**
     * Aqui temos um comportamento interessante
     *
     * se any as? Type -> se a variavel any de fato eh do tipo Type
     *      value = any as Type => o casting ocorre
     *
     * se any !is Type -> se a variavel any nao for do tipo Type
     *      value = null
     *
     *  No caso abaixo vou usar o elvis operator para evitar o resultado null
     *
     * Nas palavras de ""
     * "The safe cast returns either the smart cast value or null as result"
     *
     * expressoes equivalentes
     *
     * val s = if(any is String) any else null
     * val s = (any as? String) //(s: String?)
     *
     * */
    val value = (any as? String)?.toUpperCase() ?: orElse()
    println(value)
}

private fun ex() {
    val s = "chris"
    // aqui so sera impresso null
    println(s as? Int)
    // aqui sera lancada uma exception  java.lang.ClassCastException
    println(s as Int?)
}


fun main() {
    val s: String? = null
    /*
    safeCast("chris")

    safeCast(s)
     */

    simpleSafeCast(s) { null }
    simpleSafeCast(1) { "Null or Wrong Type" }
    ex()
}