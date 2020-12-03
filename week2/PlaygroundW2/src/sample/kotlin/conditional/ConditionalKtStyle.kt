package sample.kotlin.conditional

enum class WeatherColor(val color: Int) {
    BLUE(0), ORANGE(1), RED(2)
}

//
fun updateWeather(degrees: Int) : Pair<String, WeatherColor> {
    return when {
            degrees < 10 -> Pair("cold", WeatherColor.BLUE)
            degrees < 25 -> Pair("mild", WeatherColor.ORANGE)
            else -> Pair("hot", WeatherColor.RED)
        }
}

fun testUpdateWeather() {
    println(updateWeather(10))
}

fun main(args: Array<String>) {
   testUpdateWeather()
}