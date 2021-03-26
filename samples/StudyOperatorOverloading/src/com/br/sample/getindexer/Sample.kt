package com.br.sample.getindexer


// https://www.baeldung.com/kotlin/operator-overloading#8-get-indexer


private fun sampleGet1() {
    val contentPage = ContentPage(mutableListOf("Info1", "Info2", "Info3"))
    val page: PageMetadata<String> = DefaultPageMetadata(contentPage)

    // problema de inferencia
    // val content: String = page[0]
    val content: String = page.get<String>(0)
    println(content)
}

private fun sampleGet2() {
    val contentPage = ContentPage(mutableListOf("Info1", "Info2", "Info3"))
    val page: PageMetadata<String> = DefaultPageMetadata(contentPage)

    // mesmo problema de inferencia
    //val content: List<String> = page[0, 2]
    ///
    val content: List<String> = page.get<List<String>>(0, 2)
    println(content)
}

fun main() {
    //sampleGet1()
    sampleGet2()
}