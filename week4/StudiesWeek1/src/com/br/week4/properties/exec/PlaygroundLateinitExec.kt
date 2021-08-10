package com.br.week4.properties.exec

import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.kotlinProperty

class A {
    private lateinit var prop: String

    fun setUp() {
        prop = "value"
    }

    fun display() {
        println(prop)
    }
}

fun A.isInit() : Boolean =
    this::class.memberProperties.filter {

        it.isAccessible && it.isLateinit
    }.none {
        it.javaField?.get(this) == null
    }

fun A.allFieldsWereInit() : Boolean =
    this.run {
        javaClass.fields.map {
            val field = it
            field.isAccessible = true
            field.kotlinProperty?.isAccessible = true
            field
        }
        this::class.memberProperties.filter {
            it.isLateinit
        }.none {
            it.javaField?.get(this) == null
        }
    }

fun main(args: Array<String>) {
    val a = A()

    if (a.allFieldsWereInit()) {
        println("Already initialized")
        a.display()
    } else {
        a.setUp()
        a.display()
    }
}