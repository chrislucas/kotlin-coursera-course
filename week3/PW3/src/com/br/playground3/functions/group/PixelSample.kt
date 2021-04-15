package com.br.playground3.functions.group

data class Pixel(val r: Int, val g: Int, val b: Int)


fun groupingMatrix1DOfPixels() {
    val map = arrayOf(Pixel(244, 244, 255), Pixel(244, 244, 255))
        .histogram() { it.size }

    println(map)
}

fun groupingMatrix2DOfPixels() {
    val image = Array(3) { Array<Pixel?>(3) { null } }
    image[0][0] = Pixel(244, 244, 255)
    image[0][1] = Pixel(244, 244, 255)
    image[0][2] = Pixel(244, 244, 255)

    val map = image.histogram() { list -> list.size }
    println(map)
}

fun main() {
    groupingMatrix2DOfPixels()
}