package com.br.week5.inlinefun




private fun checkTakeUnless(q: Int) {

    /*
        T.takeUnless (fn: (T) -> Boolean)

       return if (!fn(this))
             this
        else
            null

        O bloco de codigo acima e copiada pelo compilador
        e substitui a chamada bem como o corpo da funcao lamdbda
     */
    val p = q.takeUnless { it > 10 }

    /*
        o codigo aciima apos compiladdo ficará:

        val p = if (!(q > 10)) q  else null

        veja que substitui-se a chamada a funcao lambda pelo seu corpo, para que ele
        seja executado diretamente
     */
    println(p)
    println(if (q <= 10) q else null)
}


fun main() {
    checkTakeUnless(10)
}