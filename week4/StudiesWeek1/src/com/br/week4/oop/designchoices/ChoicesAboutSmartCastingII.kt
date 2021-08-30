package com.br.week4.oop.designchoices

private fun testSmartCastVariableInitByDelegate(value: Int?) {
    var mValue: Int? by delegateInitInt(value)
    if (mValue is Int) {
        //Smart cast to 'Int' is impossible, because 'mValue' is a property that has open or custom getter
        //println(mValue * 2)
        println(mValue?.times(2) )
        mValue = 0xa
        println(mValue)
    }
}


fun main() {
    testSmartCastVariableInitByDelegate(15)
}