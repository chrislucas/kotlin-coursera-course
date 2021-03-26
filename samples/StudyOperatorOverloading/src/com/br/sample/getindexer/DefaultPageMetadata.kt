package com.br.sample.getindexer

class DefaultPageMetadata<T>(private val contentPage: ContentPage<T>) : PageMetadata<T> {

    override fun pageNumber(): Int = contentPage.data.size

    override fun pageSize(): Int = contentPage.size

    override fun elements(): MutableList<T> = contentPage.data

}