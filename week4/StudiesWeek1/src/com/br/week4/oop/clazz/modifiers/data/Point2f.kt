package com.br.week4.oop.clazz.modifiers.data

data class Point2f(val x: Double, val y: Double) {
    constructor(p: Point2f): this(p.x, p.y)
}


fun main() {
    val p = Point2f(1.0, -1.0)
    // funcao que copia a instancia
    val q = p.copy()

    println("$p, $q")
    println("${p.hashCode()}, ${q.hashCode()}")
    // call equals
    println("${p == q}")
    // checks reference
    println("${p === q}")
}
