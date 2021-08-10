package com.br.samples.inheraitance


interface Behavior {
    fun execute()
}


class MoveForward : Behavior {
    override fun execute() = println("Move Forward")
}


open class Parent
// sintaxe -> class Subclass : SuperClass()
class Child : Parent()


fun main() {

}