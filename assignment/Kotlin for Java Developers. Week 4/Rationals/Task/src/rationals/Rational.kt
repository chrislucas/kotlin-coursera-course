package rationals

import java.math.BigInteger


infix fun Int.divBy(that: Int) = Rational(BigInteger.valueOf(this * 1L), BigInteger.valueOf(that * 1L))

infix fun Long.divBy(that: Long) = Rational(BigInteger.valueOf(this), BigInteger.valueOf(that))

infix fun BigInteger.divBy(that: BigInteger) = Rational(this, that)

// TODO
fun String.toRational() = ""

data class Rational(val p: BigInteger, val q: BigInteger) {
    override fun equals(other: Any?): Boolean {
        val that = other as Rational?
        return that?.let {
            this.p.minus(it.p) == BigInteger.ZERO
                    && this.q.minus(it.q) == BigInteger.ZERO
        } ?: false
    }
}

// TODO
operator fun Rational.plus(that: Rational) = Rational(this.p + that.p, this.q + that.q)

// TODO
operator fun Rational.minus(that: Rational) = Rational(this.p + that.p, this.q + that.q)

// TODO
operator fun Rational.div(that: Rational) = Rational(this.p + that.p, this.q + that.q)

// TODO
operator fun Rational.times(that: Rational) = Rational(this.p + that.p, this.q + that.q)

// TODO
operator fun Rational.unaryMinus() = Rational(-this.p , -this.q)


operator fun Rational.rangeTo(that: Rational) =
    TODO()

operator fun Rational.contains(that: Rational) =
    TODO()

operator fun Rational.compareTo(that: Rational) =
    TODO()


fun main() {
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

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println(
        "912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2
    )
}