package com.br.week4.oop.designchoices

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Delegates
 * */

fun delegateInitInt(defaultValue: Int?): ReadWriteProperty<Nothing?, Int?> =
    object: ReadWriteProperty<Nothing?, Int?> {
        var mValue: Int? = defaultValue

        override fun getValue(thisRef: Nothing?, property: KProperty<*>): Int? = mValue

        override fun setValue(thisRef: Nothing?, property: KProperty<*>, value: Int?) {
            this.mValue = value
        }
    }

val (() -> Int?).initWitDefault: ReadWriteProperty<Nothing?, Int?>
    get() {
        return object : ReadWriteProperty<Nothing?, Int?> {
            var mValue : Int? = 0
            override fun getValue(thisRef: Nothing?, property: KProperty<*>): Int? = mValue

            override fun setValue(thisRef: Nothing?, property: KProperty<*>, value: Int?) {
                this.mValue = value
            }
        }
    }