package com.br.sample.getindexer

data class ContentPage<T>(val data: MutableList<T>) {
    val size: Int
        get() {
            var acc = 0
            for (info in data) {
                acc += info.hashCode()
            }
            return acc
        }
}
