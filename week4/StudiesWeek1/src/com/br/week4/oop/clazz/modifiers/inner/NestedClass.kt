package com.br.week4.oop.clazz.modifiers.inner

/**
 * Inner classes guardando a referencia da sua  outer class, diferente de uma nested class
 *
 * tabela de comparacao da sintaxe Java para kotlin
 *
 * Java                 -        Kotlin          -       classe declarada dentro de outra classe
 *
 * static class A               class A                     nested class
 *
 * class A                      inner class A               inner class
 *
 * Em Java se nao utilizarmos o modificador static numa classe interna teremos uma inner
 * class que guardara uma referencia para a classe que externa que a abriga (outer class)
 * Essa informacao eh importante pois pode-ocorrer vazamento de memoria (memory leak) quando
 * a inner class nao for mais utilizada mas a referencia para outer class ainda esta armazenada.
 *
 * Em kotlin o padrao eh diferente, se nao utilizar o modificador inner antes de definir a classe
 * obtem-se uma nested class ou uma classe interna porem estatica, ou seja por padrao eh
 * equivalente a uma static class Java
 *
 * */

class BTree<T : Comparable<T>>(var root: Node<T>?) {

    /**
     * class Node eh uma static class e nao guarda referencia
     * da outer class BTree
     * */
    class Node<T : Comparable<T>>(val value: T) {
        var le: Node<T>? = null
        var ri: Node<T>? = null
    }

    fun add(node: Node<T>) {
        this.root?.run {
            add(this, node)
        } ?: kotlin.run { this.root = node }
    }

    private fun add(subRoot: Node<T>?, node: Node<T>): Node<T>? {
        return when {
            subRoot == null -> {
                node
            }
            node.value > subRoot.value -> {
                subRoot.ri = add(subRoot.ri, node)
                subRoot.ri
            }
            else -> {
                subRoot.le = add(subRoot.le, node)
                subRoot.le
            }
        }
    }
}


fun main() {
    val btree = BTree<Int>(null)

    btree.add(BTree.Node(100))
    btree.add(BTree.Node(50))
    btree.add(BTree.Node(200))
    btree.add(BTree.Node(49))
    btree.add(BTree.Node(51))
    btree.add(BTree.Node(190))
    btree.add(BTree.Node(201))

    println(btree.root)
}