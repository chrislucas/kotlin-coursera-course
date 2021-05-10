package com.br.week4.properties.fields

/**
 * No caso de uma classe com um atributo mutavel sem um metodo de acesso definido explicitamente, o compilador
 * ira gerar um metodo de acesso simples na seguinte forma
 *      get() = field
 *      set(value: T) {
 *          field = value
 *      }
 *      onde T sera o tipo da propriedade mutavel, no exemplo abaixo temos a propriedade
 *      value sendo definida com uma string
 *
 * */
class SimpleAcessorsGen {
    var value = "abc"
}