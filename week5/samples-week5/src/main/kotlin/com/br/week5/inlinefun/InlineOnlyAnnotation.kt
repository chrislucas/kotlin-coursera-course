package com.br.week5.inlinefun

/*
    @kotlin.internal.InlineOnly

    Especifica que a funcao que recebe essa anotacao sempre sera copiada para o local
    onde existe uma chamada a ela, isso significa que como seu corpo será copiado
    para o local de chamada entao a funcao nao precisa ser adicionada ao arquivo .jar
    após compilar e construir a aplicacao, isso diminui o tamanho da aplicação.

    Isso implica em outra coisa: Como as funcoes nao estarao no arquivo .jar resultante,
    elas nao podem ser chamadas por um código java, é somente acessível a código kolint
 */