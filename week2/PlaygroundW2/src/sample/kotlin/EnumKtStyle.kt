package sample.kotlin

import sample.kotlin.enums.Direction
import sample.kotlin.enums.EnumUtils.genPrintValues
import sample.kotlin.enums.State
import sample.kotlin.enums.printValues
import sample.kotlin.enums.EnumUtils.valuesToString

object TestEnumAnonymousImpl {
    @JvmStatic
    fun testEnumState(state: State) {
        println(state.signal())
    }

    @JvmStatic
    fun testGetEnumValue(state: State) {
        println(State.valueOf(state.name))
    }

    @JvmStatic
    fun testGetEnumValues() = State.values()

    @JvmStatic
    fun testExtFunEnum() = State::class.printValues()
}


fun main(args: Array<String>) {
    TestEnumAnonymousImpl.testEnumState(State.RUNNING)
    TestEnumAnonymousImpl.testEnumState(State.WAITING)
    TestEnumAnonymousImpl.testGetEnumValue(State.WAITING)
    TestEnumAnonymousImpl.testGetEnumValue(State.RUNNING)

    TestEnumAnonymousImpl.testGetEnumValues().forEach { println("Value: $it") }

    TestEnumAnonymousImpl.testExtFunEnum()
    Direction::class.genPrintValues()
    println(Direction::class.valuesToString("?"))
}