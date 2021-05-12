package com.br.week4.properties.interfaces


private fun buildInternalName(id: Int) = "InternalName: $id"

/**
 * Na classe Profile o valor da propriedade internalName eh armazenado em memoria (field)
 * Na classe MediaSocialProfile p valor da propriedade internalName eh processado/calculado e retornardo
 * toda vez que a propriedade for utilizada (o metodo getter eh acionado todas as vezes)
 * */

open class Profile(val accountId: Int) : User {
    override val internalName: String = buildInternalName(accountId)
}

class SubProfile(id: Int): Profile(id)

class MediaSocialProfile(val email: String) : User {
    override val internalName: String
        get() = email.substringBefore("@")
}

fun main() {
    val profile = Profile(1)

    println(profile.internalName)

    val anotherProfile = MediaSocialProfile("chris@jmail.com")

    println(anotherProfile.internalName)
}