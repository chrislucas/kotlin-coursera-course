package com.br.playground3.samples.combinatory

import java.math.BigInteger

object CombinatoryBigInteger {

    @JvmStatic
    fun ncr1(n: BigInteger, p: BigInteger): BigInteger {
        var acc = BigInteger.ONE
        val limit = if (p > n - p) n.minus(p) else p
        var i  = BigInteger.ZERO
        while (i < limit) {
            acc = acc * n.minus(i) / (i + BigInteger.ONE)
            i += BigInteger.ONE
        }
        return acc
    }

    @JvmStatic
    private fun gdc(a: BigInteger, b: BigInteger): BigInteger {
       return if (a % b == BigInteger.ZERO) {
            b
        } else {
            gdc(b, a % b)
       }
    }

    @JvmStatic
    fun ncr2(n: BigInteger, p: BigInteger): BigInteger {
        var (q, r) = arrayOf(BigInteger.ONE, BigInteger.ONE)
        var copyP = if (n - p < p) n.minus(p) else p
        var copyN = n
        while (copyP > BigInteger.ZERO) {
            q *= copyN
            r *= copyP
            val div = gdc(q, r)
            q /= div
            r /= div
           copyN -= BigInteger.ONE
           copyP -= BigInteger.ONE
        }
        return q
    }
}