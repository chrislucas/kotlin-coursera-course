package rationals

import java.math.BigInteger


infix fun Int.divBy(that: Int) = Rational(BigInteger.valueOf(this * 1L), BigInteger.valueOf(that * 1L))

infix fun Long.divBy(that: Long) = Rational(BigInteger.valueOf(this), BigInteger.valueOf(that))

infix fun BigInteger.divBy(that: BigInteger) = Rational(this, that)

// TODO
fun String.toRational() = ""

data class Rational(val p: BigInteger, val q: BigInteger): Comparable<Rational> {
    override fun equals(other: Any?): Boolean {
        val that = other as Rational?

        return that?.run {
            val (thatN, thatD) = that
            val (thisN, thisD) = this

            val (normThatN, normThatD) = that.normalize()
            val (normThisN, normThisD) = this.normalize()

            (thatN == thisN && thatD == thisD) ||
                    (normThatN == normThisN && normThatD == normThisD)

        } ?: (that === null)
    }

    override fun toString(): String {
        return this.normalize().run {
            if (q > BigInteger.ONE) "$p/$q" else "$p"
        }
    }

    override fun hashCode(): Int {
        var result = p.hashCode()
        result = 31 * result + q.hashCode()
        return result
    }

    override fun compareTo(other: Rational): Int {
        return 0
    }
}

private fun lcm(p: Long, q: Long): Long {
    fun gcd(p: Long, q: Long): Long {
        return if (p % q == 0L) {
            q
        } else {
            gcd(q, p % q)
        }
    }
    return (p * q) / gcd(p, q)
}

private fun lcm(p: Int, q: Int): Int {
    fun gcd(p: Int, q: Int): Int {
        return if (p % q == 0) {
            q
        } else {
            gcd(q, p % q)
        }
    }
    return (p * q) / gcd(p, q)
}

private fun lcm(p: BigInteger, q: BigInteger): BigInteger = (p * q) / p.gcd(q)

private infix fun Rational.lcm(that: Rational): BigInteger = lcm(this.q, that.q)

operator fun Rational.plus(that: Rational): Rational {
    val denominator = this lcm that
    return Rational(this.p * (denominator / this.q) + that.p * (denominator / that.q), denominator)
}

operator fun Rational.minus(that: Rational): Rational {
    val denominator = this lcm that
    return Rational(this.p * (denominator / this.q) - that.p * (denominator / that.q), denominator)
}

fun Rational.normalize(): Rational {
    val gdc = p.gcd(q)
    return Rational(p / gdc, q / gdc)
}

operator fun Rational.div(that: Rational) = Rational(this.p * that.q, this.q * that.p)

operator fun Rational.times(that: Rational) = Rational(this.p * that.p, this.q * that.q)

operator fun Rational.unaryMinus() = Rational(-this.p, this.q)

class RangeRational(override val endInclusive: Rational, override val start: Rational) : ClosedRange<Rational> {

}

// Original
operator fun Rational.rangeTo(that: Rational) = RangeRational(this, that)


infix operator fun Rational.contains(range: RangeRational) = true


// Original
operator fun Rational.contains(that: Rational) = true

// Original
// operator fun Rational.compareTo(that: Rational) = 1




private fun checkAssignment() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3

    println(half < twoThirds)

    println(half in (third..twoThirds) )
    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println(
        "912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2
    )
}

private fun checkRationalPlusOperator() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)
}

private fun checkRationalMinusOperator() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val difference: Rational = half - third
    println(1 divBy 6 == difference)
}

private fun checkRationalDivOperator() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)
}


private fun checkRationalMultiplierOperator() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val product: Rational = half * third
    println(1 divBy 6 == product)
}

private fun checkAllOperators() {
    checkRationalPlusOperator()
    checkRationalMinusOperator()
    checkRationalDivOperator()
    checkRationalMultiplierOperator()
}

private fun checkContainsOperator() {

}

fun main() {
    checkAllOperators()
}