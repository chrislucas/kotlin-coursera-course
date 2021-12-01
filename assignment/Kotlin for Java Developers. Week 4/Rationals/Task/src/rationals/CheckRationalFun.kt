package rationals

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

private fun checkStringToRational() {
    /*
    println("1/2".toRational())
    println("117/1098".toRational())
    println("2/4".toRational())
    println("-1/2".toRational())
    println("23/1".toRational())
    println("23".toRational())
    println("2/1".toRational())
    println("2/8".toRational())

     */
    val p = "2/-8".toRational().normalize()
    val q = "1/-4".toRational().normalize()
    println("$p, $q, ${p == q}")
}

private fun checkCompareTo() {
    val half = 1 divBy 2
    val third = 1 divBy 3
    println(half > third)

    val twoThirds = 2 divBy 3
    println(half < twoThirds)
}

private fun checkContainsOperator() {
    val half = 1 divBy 2
    val third = 1 divBy 3
    val twoThirds = 2 divBy 3

    println(half in third .. twoThirds)
    println( (4 divBy 8) in (1 divBy 2) .. (1 divBy 2))
    println( (4 divBy 8) in (1 divBy 2) .. (1 divBy 3))
    println( (4 divBy 8) in (1 divBy 8) .. (1 divBy 2))
}

private fun checkRationalPlusOperator() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)
}

private fun checkAllOperators() {
    checkRationalPlusOperator()
    checkRationalMinusOperator()
    checkRationalDivOperator()
    checkRationalMultiplierOperator()
}

fun main() {
    checkContainsOperator()
}