package com.br.week4.oop.designchoices

import kotlin.reflect.KProperty

private fun testSmartCastVariableInitByDelegate(init: () -> Int?) {
    var mValue: Int? by init.initWitDefault
    if (mValue is Int) {
        // Smart cast to 'Int' is impossible, because 'mValue'
        // is a property that has open or custom getter
        println(mValue?.times(2))
        mValue = 15
        println(mValue)
    }
}


private fun testSmartCastVariableInitByLimitedDelegate(init: () -> Int?) {
    var mValue: Int? by init
    if (mValue is Int) {
        // Smart cast to 'Int' is impossible, because 'mValue'
        // is a property that has open or custom getter
        println(mValue?.times(2))
        mValue = 15
        println(mValue)
    }
}


private operator fun <R> (() -> R)?.setValue(r: R?, property: KProperty<*>, r1: R?) {
    println("Call setter")
}

private operator fun <R> (() -> R?)?.getValue(nothing: Nothing?, property: KProperty<*>): R? = this?.let { it() }





fun main() {
    //testSmartCastVariableInitByDelegate { 15 }
    testSmartCastVariableInitByLimitedDelegate { 15 }
}