package sample.kotlin


data class Point2f(val x: Double, val y: Double)

// https://kotlinlang.org/docs/reference/operator-overloading.html
operator fun Point2f.plus(q: Point2f): Point2f = Point2f(x + q.x, y + q.y)

operator fun Point2f.minus(q: Point2f): Point2f = Point2f(x - q.x, y - q.y)

fun Point2f.transform(q: Point2f, fn: (Point2f, Point2f) -> Point2f): Point2f = fn(this, q)


typealias BinaryTransPoint = (Point2f, Point2f, (Point2f, Point2f) -> Point2f) -> Point2f

class Point2fTransform : BinaryTransPoint {
    override fun invoke(p1: Point2f, p2: Point2f, fn: (Point2f, Point2f) -> Point2f): Point2f =
        fn(p1, p2)
}


fun main() {
    val (p, q) = arrayOf(Point2f(1.0, 2.9), Point2f(2.3, 23.0))
    var s = Point2fTransform().invoke(p, q) { np, nq -> np + nq }

    val transform: BinaryTransPoint = Point2fTransform()

    s =transform.invoke(p, q) { np, nq -> np - nq }
    println(s)

}