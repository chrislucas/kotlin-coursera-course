package com.br.sample.unaryop

import com.br.sample.data.*


private fun samplePlusAssignment() {
    val p = MutablePoint2D(2.0, 3.75)
    val q = -p
    p += q
    println("P: $p")
}

private fun sampleMinusAssignment() {
    val p = MutablePoint2D(2.0, 3.75)
    val q = -p
    p -= q // p - (-q)  https://edu.gcfglobal.org/pt/somar-e-subtrair/regra-dos-simbolos-ou-sinais/1/
    println("P: $p")
}

private fun samplePlus() {
    val p = MutablePoint2D(2.0, 3.75)
    val q = -p
    println("${p + q}")
}

private fun moving() {
    val q = Point2D(2.0, 3.654) * 2
    println(q)

    // Comutatividade = a * b == b * a
    // Operadores sobrecarregados nao sao comutativos, ou seja trocar a order dos operandos
    // pode nao funcionar, como no caso abaixo. Para tal, sobreescreve um operador
    // do segundo operando

    println( 2 * Point2D(2.0, 3.654))

    println( 2 + Point2D(2.0, 3.654))

}

fun main() {
    moving()
}