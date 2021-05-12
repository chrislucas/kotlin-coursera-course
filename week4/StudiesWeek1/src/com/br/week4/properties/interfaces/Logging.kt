package com.br.week4.properties.interfaces
/**
 * interfaces tem por definicao a caracterisca de ser "open" do contrario nao poderia
 * ser implementadas por classes ou se tornarem super classes de outras interfaces.
 * Por consequencia suas propriedades e contratos tambem sao "open" e por essa caracteristica
 * nao podemos utilizar  smartcast
 *
 * */
private fun analyzeUserSession(session: Session) {
    if (session.user is Profile) {
        /**
         * Fizemos o teste acima que diz que a propriedade user eh do tipo Profile
         * poderias tentar acessar a propriedade accountId de user, entrentato
         * user poderia ser uma subclasse de Profile
         * */

        // Smart cast to 'Profile' is impossible, because 'session.user' is a property that has open or custom getter
        //println(session.user.accountId)
        /**
         * Torna-se inseguro utilizar o smartcast para acessar uma propriedade de uma classe que implementa
         * uma interface por ela ser uma propriedade open e poder ter um getter personalizado
         * */

        // casting explicito para resoplver o problema do smartcast
        // mas isso pode causar uma exception
        //println("AccountId: ${(session.user as Profile).accountId}")

        // para resolver o problema do smartcast uma outra forma mais segura
        val user = session.user
        if (user is Profile) {
            println("AccountId: ${user.accountId}")
        }
        println("IN: ${session.user.internalName}")

    }
}

class SessionProfile(private val accountId: Int) : Session {
    override val user: User = Profile(accountId)
}

class SessionSubProfile(private val id: Int):Session {
    override val user: User = SubProfile(id)
}

class SessionMediaSocialProfile(private val email: String) : Session {
    override val user: User = MediaSocialProfile(email)
}

fun main() {
    analyzeUserSession(SessionProfile(1))
    analyzeUserSession(SessionSubProfile(12))
    analyzeUserSession(SessionMediaSocialProfile("chris@jmail.com"))
}