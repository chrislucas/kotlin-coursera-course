package com.br.week4.oop.objects

/**
 * https://www.baeldung.com/kotlin/lambda-expressions
 * */


interface OnClickListener {
    fun onClick()
}

// object expression
val clickListener = object : OnClickListener {
    override fun onClick() {
        println("Clicked")
    }
}

val lambdaClickListener : () -> Unit = {
    println("Clicked")
}

private fun execute(listener: OnClickListener) {
    listener.onClick()
}

private fun testRunnable() {
    Runnable(lambdaClickListener).run()
    Runnable { println(0xff) }.run()
}

private fun testObjectExpression() {
    execute(object : OnClickListener {
        override fun onClick() {
            println(0xff)
        }
    })

    execute(clickListener)
}


fun main() {




}