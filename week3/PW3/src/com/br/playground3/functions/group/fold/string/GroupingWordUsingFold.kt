package com.br.playground3.functions.group.fold.string

import com.br.playground3.exts.getAllFiles
import com.br.playground3.exts.joinContent
import com.br.playground3.exts.log
import java.io.File
import java.nio.file.Paths
import kotlin.io.path.ExperimentalPathApi


/**
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/fold.html
 *
 * Usando: ext fun para Grouping
 *  fun <T, K, R> Grouping<T, K>.fold(initValueSelector: (K, T) -> R, m: (K, R) )
 * */

private fun groupingWordsByFirstLetter(words: List<String>) {
    val transform = { key: Char, _: String ->
        Pair(key, mutableListOf<String>())
    }
    val group = words.groupingBy { word -> word.first() }
    val map = group.fold(transform) { _, acc: Pair<Char, MutableList<String>>, currentValue ->
        acc.apply {
            this.second.add(currentValue)
        }
    }
    map.log()
}

/**
 * Usando ext fun for grouping
 *
 *  fun <T, K, R> Grouping<T, K>.fold(init: R, (acc: R, element: T) -> Map<K, R>)
 *
 *  essa ext fun utiliza a funcao aggregate para gerar uma map
 * */
private fun groupingWordsByFirstLetterV2(words: List<String>) {
    val map = words.groupingBy { it.first() }
        .fold(listOf<String>()) { acc, e ->
            acc + e
        }

    map.log()
}

private fun filterByTXTFiles(files: List<File>): List<File> {
    val hasTxtSuffix = Regex("\\w+\\.txt")
    return files.filter { file ->
        hasTxtSuffix.matches(file.name)
    }
}

private fun getContentAllFiles(path: String): List<String> {
    val files = mutableListOf<File>()
    getAllFiles(File(path), files)
    return files.joinContent().split(" ")
}

@OptIn(ExperimentalPathApi::class)
private fun getContent(path: String): List<String> {
    return Paths.get(path)
        .getAllFiles()
        .joinContent()
        .split(" ")
}

private fun groupingWords() {
    val words = getContentAllFiles("raw/")
    groupingWordsByFirstLetter(words)
    groupingWordsByFirstLetterV2(words)
}

private fun groupingWordsV2() {
    val words = getContent("raw/")
    groupingWordsByFirstLetter(words)
    groupingWordsByFirstLetterV2(words)
}

fun main() {
    groupingWords()
}