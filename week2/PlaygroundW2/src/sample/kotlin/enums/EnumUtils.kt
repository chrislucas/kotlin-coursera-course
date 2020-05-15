package sample.kotlin.enums

import kotlin.reflect.KClass

object EnumUtils {
    fun <T: Enum<*>> KClass<T>.genPrintValues() = this.java.enumConstants.forEach {
        println("Value: $it, Name: ${it.name}")
    }

    inline fun <reified T : Enum<T>> KClass<T>.valuesToString(sep: String= "|") : String = enumValues<T>()
        .joinToString (separator = sep) { it.name }
}