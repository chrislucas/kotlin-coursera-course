package com.br.week4.operators.conventions.comparables

import kotlin.math.abs

class SimpleWrapperData(val value: Double) {
    override fun equals(other: Any?): Boolean {
        return if (other is SimpleWrapperData) {
            this.compareTo(other) == 0
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

//operator fun SimpleWrapperData.compareTo(other: SimpleWrapperData) = this.value.compareTo(other.value)

// criando uma ext compareTo com a assinatura correta da para compara instancias da classe
// SimpleWrapperData usando operadores < <= >= e sobreescrevendo o metodo equal
// da para compaarar com == tendo um comportamento adequado ao que se deseja


operator fun SimpleWrapperData.compareTo(other: SimpleWrapperData): Int {
    return when {
        this.value.almostEquals(other.value) -> {
            0
        }
        this.value - other.value < 0 -> {
            -1
        }
        else -> {
            1
        }
    }
}

fun Double.almostEquals(other: Double, eps: Double = 10E-6): Boolean = abs(this - other) < eps


fun main() {
    val data1 = SimpleWrapperData(1.5)
    //val data2 = SimpleWrapperData(1.5000000000000000001)
    val data2 = SimpleWrapperData(1.500001)
    println(data1 == data2)


    val data9 = SimpleWrapperData(1.5)
    val data10 = SimpleWrapperData(1.50001)
    println(data9 == data10)

    val data3 = SimpleWrapperData(1.5)
    val data4 = SimpleWrapperData(1.6)
    println(data3 < data4)


    val data5 = SimpleWrapperData(1.5002)
    val data6 = SimpleWrapperData(1.5001)
    println(data5 > data6)


    val data7 = SimpleWrapperData(1.500002)
    val data8 = SimpleWrapperData(1.500001)
    println(data7 == data8)

}