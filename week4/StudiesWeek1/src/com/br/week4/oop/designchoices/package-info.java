package com.br.week4.oop.designchoices;


/**
 * Why there is no full pattern matching support in kotlin ?
 *
 * Segundo o instrutor, esse nao era um recurso que foi julgado importante e que seria muito utilizado,
 * dessa forma nao foi priorizada a implementacao na linguagem (Uma decisao de design de linguagem pragmantica)
 *
 * O que se ve de pattern matching na linguagem e:
 *  - wgen expression
 *  - smart cast
 *  Na visao do instrutor, esses 2 recursos cobrem muitos casos de uso simples de pattern matching
 *
 *  Foi sacrificado analise de estruturas mais "aninhadas" (nested) entre outros recursos
 *  foi deixado para versoes posteriores. O projeto ainda existe e foi construido de tal forma a ser inserido na linguagem
 *
 * https://stackoverflow.com/questions/2502354/what-is-pattern-matching-in-functional-languages
 *
 * pattern matching support in programming languages
 * https://programmingideaswithjake.wordpress.com/2016/08/27/improved-pattern-matching-in-kotlin/
 *
 * What languages support pattern matching?
 * https://en.wikipedia.org/wiki/Pattern_matching
 *
 * Why is pattern matching prevalent only in functional programming languages?
 * https://www.quora.com/Why-is-pattern-matching-prevalent-only-in-functional-programming-languages
 *
 * Pattern Matching in your language?
 * https://www.reddit.com/r/ProgrammingLanguages/comments/f4gfum/pattern_matching_in_your_language/
 *
 * A Case for Pattern Matching
 * https://medium.com/digitalfrontiers/a-case-for-pattern-matching-b43a5c9796b8
 * */



/**
 *
 * Why companion object ?
 *
 * Explicacao do instrutor sobre essa decisao de design
 *
 * 1) Eh estranho uma classe com membros estaticos (java) que nao sao acessados por sua
 * definicao por uma instancia
 * 1.1) fica mais estranho quando vc tem uma classe com somente membros estaticos
 *
 * 2) Afim de se livrar desse tipo de situacao (classes com membros estaticos) uma das
 * decisoes foi permiter top-level functions (aquelas funcoes "fora/jogadas" de classes)
 * assim vc nao gasta tempo criando uma classe so para adicionar uma funcao estatica
 *
 * 3) Mas por existir a necessidade (talvez seria melhor chamar de "costume")
 * de chamar essas funcoes usando a sintaxe Classe.metodo(), criou-se a idea
 * de companion object.
 *
 * 4) Mas o conceito de Object eh mais poderoso pois podemos fazer com que ele
 * extenda uma classe ou implemente uma interface
 *
 * 5) E ainda define um tipo, o que permite criar extensions para ela o que permite
 * a criacao de novas APIS para a classe companion por fora
 *
 * */