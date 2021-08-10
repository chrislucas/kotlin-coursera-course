package com.br.week4.oop.modifiers


/**
 * Sem o modificador open, essa classe em bytecode sera
 * definida como final class
 * */
class Sample {
    /**
     * Em java nao existe um modificador de acesso similar a internal
     * entao o compilador tornara esse metodo public final no
     * bytecode final e o nome do metodo sera modificado
     *
     * execute$Nome_Do_Modulo_Ou_Projeto_Que_A_Contem
     * execute$StudiesWeek1
     *
     * */
    internal fun execute() = println(0xff)
}


/**
 * Top level method
 * */
fun main() {
    Sample().execute()
}