package sample.kotlin

/**
 * Data class declaram automaticamente funcoes denomeidadas
 * component1 ... componentN, que sao utilizadas para podermos usar
 * as 'destructuring declaration'
 * */
data class Point2D(val x: Double, val y: Double)

class Point3D(val x: Double, val y: Double, val z: Double) {
    operator fun component1(): Double = x
    operator fun component2(): Double = y
    operator fun component3(): Double = z
}

// https://kotlinlang.org/docs/reference/multi-declarations.html
fun testDestructuringDeclaration() {
    val(desc: String, color: WeatherColor) = Pair("cold", WeatherColor.BLUE)
    /**
     * Ao usar 'destructuring declaration'
     * val p = Point2D(10.0, -10.0)
     * val (x: Double, y: Double) = p
     *
     * esse declaracacao e compilada da seguinte forma
     * val x = p.x (ou o metodo que retorna x)
     * val y = p.y (idem para y)
     *
     * Isso funciona be
     *
     * */
    println("$desc, $color")

    val (x: Double, y: Double) = Point2D(20.0, 10.0)
    println("$x, $y")

    val (a: Double, b: Double, z: Double) = Point3D(x, y, -10.0)
    println("$x, $y, $z")
}

fun testDestructuingInMapDS() {

    val map = mutableMapOf<Int,Pair<String, WeatherColor>>()

    for (i in 0 .. 100) {
        map[i] = when  {
            i < 10 -> Pair("cold", WeatherColor.BLUE)
            i < 25 -> Pair("mild", WeatherColor.ORANGE)
            else -> Pair("hot", WeatherColor.RED)
        }
    }
    
    // destructurin declaration
    for ( (i,p) in map) {
        println("$i, $p")
    }
}


fun main(args: Array<String>) {
    testDestructuingInMapDS()
}