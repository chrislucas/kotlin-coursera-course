package com.br.playground3.samples.combinatory

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.roundToLong

object Combinatory {
    @JvmStatic
    fun ncr(n: Long, p: Long): Long {
        var acc = 1L
        val limit = if (p > n - p) n - p else p
        // n ! / (n - p)! * p!
        // n * (n-1) * (n-2) ... n-p+1 / p * p-1 * p-2 ... 1
        for (i in 0 until limit) {
            acc = acc * (n - i) / (i + 1)
        }
        return acc
    }

    @JvmStatic
    fun ncr2(n: Long, p: Long): Long {
        var (acc, pp, nn) = arrayOf(1.0, p * 1.0, n * 1.0)
        // n/p * (n-1 / p-1) ... n-p+1/1
        while (nn >= n - p + 1) {
            acc *= nn / pp
            nn -= 1.0
            pp -= 1.0
        }
        return acc.roundToLong()
    }

    @JvmStatic
    private fun gdc(a: Long, b: Long): Long {
        return if (a % b == 0L) {
            b
        } else {
            gdc(b, a % b)
        }
    }

    // https://www.geeksforgeeks.org/program-to-calculate-the-value-of-ncr-efficiently/
    @JvmStatic
    fun ncr3(n: Long, p: Long): Long {
        var (x, y) = arrayOf(1L, 1L)
        var limit = if (p > n - p) n - p else p
        var copyN = n
        while (limit > 0) {
            x *= copyN
            y *= limit
            val divisor = gdc(x, y)
            x /= divisor
            y /= divisor
            limit -= 1
            copyN -= 1
        }
        return x
    }
}

fun sample1() {
    arrayOf(
        Pair(10L, 7L),
        Pair(12L, 2L),
        Pair(123L, 2L),
        Pair(100L, 2L),
        Pair(30L, 15L),
        Pair(30L, 14L),
        Pair(50L, 25L),
        Pair(150L, 25L),
    ).forEach { (n, p) ->
        println(
            "Pair %2s: %d, %d, %d, %d, %d, %f".format(
                n to p,
                Combinatory.ncr(n, p),
                Combinatory.ncr2(n, p),
                Combinatory.ncr3(n, p),
                CombinatoryBigInteger.ncr1(BigInteger.valueOf(n), BigInteger.valueOf(p)),
                CombinatoryBigInteger.ncr2(BigInteger.valueOf(n), BigInteger.valueOf(p)),
                CombinatoryBigDecimal.ncr(BigDecimal.valueOf(n), BigDecimal.valueOf(p))
            )
        )
    }
}

fun sample2() {
    arrayOf(
        (BigInteger.valueOf(1450) to BigInteger.valueOf(45))
        , (BigInteger.valueOf(1450) to BigInteger.valueOf(415))
        , (BigInteger.valueOf(1450) to BigInteger.valueOf(200))
        , (BigInteger.valueOf(1450) to BigInteger.valueOf(100))
        , (BigInteger.valueOf(1450) to BigInteger.valueOf(60))
        , (BigInteger.valueOf(1450) to BigInteger.valueOf(50))
        , (BigInteger.valueOf(10000) to BigInteger.valueOf(45))
    )
        .forEach { (p, q) ->
            println("ncr%s = %d".format(p to q, CombinatoryBigInteger.ncr2(p, q)))
        }
}

fun main() {
    sample2()
}