package sample.kotlin

/**
 * https://kotlinlang.org/docs/reference/lambdas.html#instantiating-a-function-type
 *
 * Function type With ou Without Receiver sao intercambiaveis ou equivalentes
 * Assim
 * uma variavel que espera o tipo (A, B) -> C pode receber A.(B) -> C sem problemas
 * */

data class Point3f(val x: Double, val y: Double, val z: Double)

// (Point3f, Double, (Point3f, Double) -> Point3f) -> Point3f
val transform: Point3f.(Double, (Point3f, Double) -> Point3f) -> Point3f = { value, fn ->
    fn(this, value)
}
/**
 * a funcao abaixo ficou desnecessariamente complexa, mas foi o exemplo que
 * venho a minha cabeca para explorar a ideia de poder passar uma Function With Receiver
 * como argumento para uma funcao que sem receiver e o compilador entender/inferir
 *
 * */
fun Point3f.applyTransformation(
    fn: (Point3f, Double, (Point3f, Double) -> Point3f) -> Point3f,
    value: Double,
    composite: (Point3f, Double) -> Point3f
): Point3f {
    return fn(this, value, composite)
}


fun sampleInterchangeFunctions() {
    /**
     * (A, B,(A, B) -> A) -> A = A.(B, (A, B) -> A) -> A
     * */
    val translate: (Point3f, Double, (Point3f, Double) -> Point3f) -> Point3f = transform
    val p = Point3f(1.0, 2.0, 3.0)
    val c = -4.5
    val newP = translate(p, c) { p, offset ->
        Point3f(p.x + offset, p.y + offset, p.z + offset)
    }
    println(newP)

    // testando uma ideia doida, so para ver ate onde vai

    val newP2 = newP.applyTransformation(transform, c * -1.0) { p, offset ->
        Point3f(p.x + offset, p.y + offset, p.z + offset)
    }

    println(newP2)
}

fun main() {
    sampleInterchangeFunctions()
}