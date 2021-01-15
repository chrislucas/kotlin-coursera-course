package sample.kotlin.ext


fun testJoinToString() {
    var jointed = listOf("a", "c", "e")
        .joinToString(separator = "|", prefix = "{", postfix = "}")

    println(jointed)

    val disjointed = jointed.split("").toList()

    jointed = disjointed.joinToString(separator = "?", prefix = "[", postfix = "]") {
        data -> if (data.matches("\\w+".toRegex())) "<$data>"  else ""
    }
}

fun extIterateWithIndex() {

    /**
     * IndexedValue<V>(i: Int, v: V)
     * */

    for ( p: IndexedValue<String> in listOf("a", "c", "e").withIndex()) {
        println(p)
    }
}

// artigo interessante sobre ext function
// https://android.jlelse.eu/the-ugly-truth-about-extension-functions-in-kotlin-486ec49824f4

data class Point2D(val x: Double, val y: Double) {

    companion object {}
    /***
     * Se criarmos uma funcao membro da classe com o mesmo nome e assinatura
     * de uma ext function, a funcao membro sempre sera executada no lugar da ext
     * */
    fun log() = println("Point2D($x, $y)")

    override fun toString(): String = "P($x, $y)"
}

// ainda tentando entender qual o proposito de se criar uma ext para um Companion
// static extension function
fun Point2D.Companion.staticExtFunction() {
    println("Ext Function | Static Method (without any propose)")
}

fun Point2D.log() { println(this) }

fun main() {
    //extIterateWithIndex()
    val p = Point2D(2.0, 3.0)
    p.log()
    Point2D.staticExtFunction()
}