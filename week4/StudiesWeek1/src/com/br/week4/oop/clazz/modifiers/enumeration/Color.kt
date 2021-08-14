package com.br.week4.oop.clazz.modifiers.enumeration

enum class Color {
    BLUE, GRAY, ORANGE
}

enum class Weather { COLD, HOT, MILD }

val Color.weather
    get() =
        when (this) {
            Color.BLUE -> Weather.COLD
            Color.GRAY -> Weather.MILD
            Color.ORANGE -> Weather.HOT
        }


fun main() {

    println(Color.BLUE.weather)
}