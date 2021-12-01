package com.br.week4.range

/*
    https://www.baeldung.com/kotlin/custom-range-iterator
 */


data class Color(val rgb: Int) : Comparable<Color> {
    override fun compareTo(other: Color): Int {
        return rgb - other.rgb
    }

    override fun toString(): String {
        return String.format("0x%08x", rgb)
    }

    operator fun plus(value: Int): Color = Color(this.rgb + value)

    operator fun rangeTo(that: Color) = ColorRange(this, that)

    operator fun inc(): Color = Color(rgb + 1)
}

class ColorRange(
    start: Color,
    endInclusive: Color,
) : ColorProgression(start, endInclusive, 1), ClosedRange<Color> {

    override operator fun contains(color: Color): Boolean {
        return start.rgb <= color.rgb && color.rgb <= endInclusive.rgb
    }
}

open class ColorProgression internal constructor(val start: Color, val endInclusive: Color, val step: Int) :
    Iterable<Color> {

    override fun iterator(): Iterator<Color> {
        return object : Iterator<Color> {
            var s = start

            override fun hasNext(): Boolean = s <= endInclusive

            override fun next(): Color {
                s += step
                return s
            }
        }
    }
}

private infix fun ColorProgression.step(step: Int): ColorProgression {
    if (step < 0)
        throw IllegalArgumentException("")
    return ColorProgression(this.start, this.endInclusive, step)
}


private fun iterate() {

    val a = Color(0x000000)
    val b = Color(0x000010)

    for (color in a..b step 2) {
        println(color)
    }
}


private fun checkContains() {
    println(Color(0x0004) in Color(0x0000)..Color(0x0004))
    println(Color(0x0000) in Color(0x0000)..Color(0x0004))
    println(Color(0x0005) in Color(0x0000)..Color(0x0004))
}


fun main() {
    checkContains()
}