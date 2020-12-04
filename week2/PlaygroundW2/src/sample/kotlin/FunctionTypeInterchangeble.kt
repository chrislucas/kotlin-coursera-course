package sample.kotlin

/**
 * https://kotlinlang.org/docs/reference/lambdas.html#instantiating-a-function-type
 *
 * Function type With ou Without Receiver sao intercambiaveis ou equivalentes
 * Assim
 * uma variavel que espera o tipo (A, B) -> C pode receber A.(B) -> C sem problemas
 * */

data class Point3f(val x: Double, val y: Double, val z: Double)

val transform: Point3f.(Double, (Point3f, Double) -> Point3f) -> Point3f = { value, fn ->
    fn(this, value)
}


fun main() {
    /**
     * (A, B,(A, B) -> A) -> A = A.(B, (A, B) -> A) -> A
     * */
    val translate: (Point3f, Double, (Point3f, Double) -> Point3f) -> Point3f = transform

    val p = translate(Point3f(1.0, 2.0, 3.0), -4.5) { p, offset ->
        Point3f(p.x + offset, p.y + offset, p.z + offset)
    }

    println(p)
}