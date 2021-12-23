package com.br.week5.lambdawithreceiver

import sun.jvm.hotspot.opto.Block_List

/*
    Lamnda with receiver is a very powerfull mechanism that allows us to create domain
    specific language in kotlin
 */


interface Block

class Table : Block

data class TR(val tds: List<TD>): Block

class TD(val blocks: List<Block>? = null): Block

class Label(val text: String): Block


private fun html(createBlock: () -> Block) = createBlock()

private fun table(createBlock: () -> Table) = createBlock()

private fun tr(createBlock: () -> TR) = createBlock()

private fun td(createBlock: () -> TD) = createBlock()


private fun build() {
    html {
        table {
            tr {
                val tds = listOf(
                    td { TD(listOf(Label("..."))) },
                    td { TD() },
                    td { TD() }
                )
                TR(tds)
            }
            Table()
        }
    }
}


fun main() {
    build()
}