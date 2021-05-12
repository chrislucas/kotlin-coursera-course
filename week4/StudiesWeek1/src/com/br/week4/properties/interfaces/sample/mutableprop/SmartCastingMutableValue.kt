package com.br.week4.properties.interfaces.sample.mutableprop

import javax.swing.text.Document

interface UserSys {
    val nickname: String
}

class Employee(val hash: String): UserSys {
    override val nickname: String = hash
}


data class SocialDocument(val name: String, val id: String)

data class VisitorId(val socialDocument: SocialDocument)

class Visitor(visitorId: VisitorId): UserSys {
    override val nickname: String = visitorId.toString()
}

interface SystemSession {
    var userSys: UserSys
}

open class ModuleMarketSession(override var userSys: UserSys): SystemSession



fun main() {
    val session = ModuleMarketSession(Employee("138758763147987987831125888"))

    /**
     * smartcast  nao funciona com mutable properties porque o seu valor pode mudar
     * podendo ter um erro em tempo de execucao
     * */
    if (session.userSys is Employee) {
        // o condicional acima testou se yserSys eh um Employee ou subtupo dele
        // na sequencia, pelo fato da propriedade ser mutavel eu posso tranformar
        // 'userSys' num Visitor que nao tem hash, logo se o smartcast nao avalia-se
        // isso em tempo de compilacao ocorreria um erro em tempo de execucao
        session.userSys = Visitor(VisitorId(SocialDocument("chris", "#123")))
        // a linha abaixo na compila
        //println("Hash: ${session.userSys.hash}")

        // passando userSys para uma variavel imutavel o smartcast passa a funcionar
        val user = session.userSys
        if (user is Employee) {
            println("Hash: ${user.hash}")
        }
    }
}