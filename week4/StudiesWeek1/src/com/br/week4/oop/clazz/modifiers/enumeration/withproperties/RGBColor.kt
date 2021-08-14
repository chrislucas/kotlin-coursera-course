package com.br.week4.oop.clazz.modifiers.enumeration.withproperties

enum class RGBColor(val r: Int, val g: Int, val b: Int) {
    /**
     * Para separar a lista de constantes do enum de membros da classe usa-se
     * o ;
     * */
    BLUE(0, 0, 255), GREEN(0, 255, 0), RED(255, 0, 0);

    // member function
    fun rgb() = (r * 256 + g) * 256 + b

    override fun toString(): String = "${this.name}($r, $g, $b)"
}


fun main() {
    println(RGBColor.RED.rgb())
    println(RGBColor.GREEN.rgb())
    println(RGBColor.BLUE.rgb())
    println(RGBColor.RED)
    println(RGBColor.GREEN)
    println(RGBColor.BLUE)
}