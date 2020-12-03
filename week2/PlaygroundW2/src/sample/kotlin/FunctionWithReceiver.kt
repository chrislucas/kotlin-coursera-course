package sample.kotlin


// Function Literal with receiver:
// https://kotlinlang.org/docs/reference/lambdas.html#function-literals-with-receiver
val op: Int.(Int, (Int, Int) -> Int) -> Int = { q, op -> op(this, q) }


fun main() {
    val (p, q) = arrayOf(10, 25)
    val r = op(p, q) { p, q -> p + q }
    println(r)
    val s = p.op(q) { p, q -> p or q }
    println(s)
}