package com.br.week5.funwithreceiver.let

interface Session {
    fun getApplication(): String
    val user: User
}


class FacebookSession(override val user: User = FacebookUser("FaceBronx User")) : Session {
    override fun getApplication() = "FaceBronx"
}

class TwitterSession(override val user: User = TwitterUser("Twitter User")) : Session {
    override fun getApplication() = "Twipster"
}

abstract class User(val name: String)

class FacebookUser(name: String) : User(name)

class TwitterUser(name: String) : User(name)


private fun checkSessionApp(session: Session) {
    (session.user as? FacebookUser)?.let {
        println(it.name)
    } ?: println("Non FacebookUser: $session")
}

fun main() {
    checkSessionApp(FacebookSession())
    checkSessionApp(TwitterSession())
}