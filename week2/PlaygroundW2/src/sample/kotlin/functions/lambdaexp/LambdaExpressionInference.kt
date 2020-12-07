package sample.kotlin


// aqui o compilador consegue inferir que a lambda eh da seguinte forma
// (Int, Int, (Int, Int) -> Int) -> Int = {}
val fn = { p: Int, q: Int, fn: (Int, Int) -> Int ->
    fn(p, q)
}

fun main() {
    println(fn(1, 23) { p, q -> p xor q })
}
