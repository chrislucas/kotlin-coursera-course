package com.br.week4.properties.fields

enum class State { ON, OFF }

class StateLogger {
    private var isOn = false
        get() {
            println("$field")
            return field
        }

    var state: State
        get() = if (isOn) State.ON else State.OFF
        set(value) {
            isOn = value == State.ON
        }
}


fun main() {
    val stateLogger = StateLogger()
    stateLogger.state = State.ON
    println(stateLogger)
}