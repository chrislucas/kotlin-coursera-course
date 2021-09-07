package com.br.week4.oop.clazz.modifiers.inner

class Graph {

    fun log() {
        println(0xff)
    }

    inner class AdjacencyMatrix {
        fun log() {
            // como acessar a referencia da outer class
            this@Graph.log()
        }
    }

    inner class AdjacencyList {
        fun log() {
            this@Graph.log()
        }
    }
}


fun main() {

    val graph = Graph()
    graph.log()
    val matrix = graph.AdjacencyMatrix()
    matrix.log()
    val adjList = graph.AdjacencyList()
    adjList.log()

}