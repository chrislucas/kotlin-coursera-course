package com.br.week4.properties

import com.br.week4.properties.model.Point2D

/**
 * prooerty = field + acceessor(s)
 *
 * val = field + getter
 * var = field + getter + setter
 *
 * sobre field, properties, attributes e variables em Java
 * https://stackoverflow.com/questions/10115588/what-is-the-difference-between-field-variable-attribute-and-property-in-java
 * https://study.com/academy/lesson/java-fields-vs-properties.html#:~:text=A%20Java%20property%20is%20also,that%20access%20is%20not%20restricted.
 *
 * ""
 *
 * */

class KotlinClassSample {
    /**
     * propriedades
     * p, q
     * em kotlin nao precisamos definir metodos de acesso, eles ja sao definidos
     * pelo compilador e existem de forma implicita.
     * */
    val p = 0
    var q: String? = null
}

private fun createPoint2D() {
    val pq = Point2D(2.0, 4.5)

    pq.x = 10.0
    pq.y = -5.0

    println(pq)
}

fun main() {
    createPoint2D()
}