package com.br.week4.operators.overloading


/**
 * += plusAssign
 *
 * */


operator fun MutableGeoPoint2D.plusAssign(other: GeoPoint2D) {
    this.x += other.x
    this.y += other.y
}

operator fun MutableGeoPoint2D.plusAssign(other: MutableGeoPoint2D) {
    this.x += other.x
    this.y += other.y
}

private fun testPlusAssignWithImmutableMemberClass() {
    var a = GeoPoint2D(1.0, 2.5)
    val q = GeoPoint2D(2.0, -2.0)

    /**
     * se a operacao + for apliaca entre instancias de uma classe
     * cujo seus membros sao imutaveis, o operador que sera chamado eh o
     * plus(T) pq ele cria uma nova instancia
     * */

    a += q
    println(a)
}

private fun testPlusAssign() {
    val list = listOf(1, 2, 3)
    /**
     * Aqui o operador plus eh chamado para criar uma nova lista
     * ja que List eh imutavel
     *
     * */
    val newList = list + 4
    println(newList)

    val mutableList = mutableListOf(1, 2, 3)
    /**
     * Aqui o operador plusAssign eh chamado ja que MutableList eh mutavel
     * sendo possivel adicionar items a ela sem a necessidade de criar uma nova instancia]
     * */
    mutableList += 4
}

private fun testPlusAssignWithMutableMemberClass() {
    val p = MutableGeoPoint2D(1.0, 2.0)
    p += GeoPoint2D(-1.0, -.5)
    println(p)
}

private fun testPlusAssignWithMutableMemberClass2() {
    val p = MutableGeoPoint2D(1.0, 2.0)
    p += MutableGeoPoint2D(-1.0, -.5)
    println(p)
}


fun main() {
    testPlusAssignWithMutableMemberClass2()
}