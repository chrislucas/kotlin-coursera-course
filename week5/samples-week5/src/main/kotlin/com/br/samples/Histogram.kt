package com.br.samples

private inline fun <T> readValue(transform: (String) -> T) = readLine()!!.run(transform)

private inline fun <T> readValues(delimiter: String = " ", transform: (String) -> T) =
    readLine()!!.split(delimiter).map { transform(it) }

private inline fun testCases(times: Int, exec: (Int) -> Unit) =
    repeat(times, exec)


fun main() {
    testCases(readValue(String::toInt)) {
        val size = readValue(String::toInt)
        val values = readValues(transform = String::toInt)
/*
        val map = mutableMapOf<Int, Int>()

        values.forEach {
            map[it] = map[it]?.plus(1) ?: 1
        }

        for ( (k, v) in map) {private inline fun <T> readValue(transform: (String) -> T) = readLine()!!.run(transform)

private inline fun <T> readValues(delimiter: String = " ", transform: (String) -> T) =
    readLine()!!.split(delimiter).map { transform(it) }

private inline fun testCases(times: Int, exec: (Int) -> Unit) =
    repeat(times, exec)


fun main() {
    testCases(readValue(String::toInt)) {
        val size = readValue(String::toInt)
        val values = readValues(transform = String::toInt)
/*
        val map = mutableMapOf<Int, Int>()

        values.forEach {
            map[it] = map[it]?.plus(1) ?: 1
        }

        for ( (k, v) in map) {
            println("$k, $v")
        }

 */
        val map = values.groupingBy { it }.eachCount()

        if (map.keys.size == size) {
            println(size)
        } else {
            if (map.keys.size == 1 && map[0] != null) {
                println(1)
            } else {
                var acc = 0
                for (p in map) {
                    if (map[-(p.key)] == null && p.value > 1) {
                        acc += 1
                    }
                }
                println(acc + map.keys.size)
            }
        }
    }
}
            println("$k, $v")
        }

 */
        val map = values.groupingBy { it }.eachCount()

        if (map.keys.size == size) {
            println(size)
        } else {
            if (map.keys.size == 1 && map[0] != null) {
                println(1)
            } else {
                var acc = 0
                for (p in map) {
                    if (map[-(p.key)] == null && p.value > 1) {
                        acc += 1
                    }
                }
                println(acc + map.keys.size)
            }
        }
    }
}