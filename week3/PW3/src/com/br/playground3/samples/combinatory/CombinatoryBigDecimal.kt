package com.br.playground3.samples.combinatory

import java.math.BigDecimal

object CombinatoryBigDecimal {

    // n/p * (n-1 / p-1) ... n-p+1/1
    @JvmStatic
    fun ncr(n: BigDecimal, p: BigDecimal): BigDecimal {
        var (acc, pp, nn) = arrayOf(BigDecimal.ONE, p, n)
        while (nn >= n - p + BigDecimal.ONE) {
            acc *=  nn / pp
            nn -= BigDecimal.ONE
            pp -= BigDecimal.ONE
        }
        return acc
    }
}