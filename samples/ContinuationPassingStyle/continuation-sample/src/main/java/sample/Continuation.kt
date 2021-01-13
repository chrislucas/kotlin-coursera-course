package sample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    val job = GlobalScope.launch {
        println(0xff)
    }

    job.start()
}