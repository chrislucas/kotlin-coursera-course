package com.br.samples.constructors.visibility


// primary internal constructor
class User internal constructor(mId: String, mName: String) {

    val id: String = mId
    val name: String = mName
    var hashInstance: String? = null

    // Secondary constructor
    internal constructor(mId: String, mName: String, mHash: String) : this(mId, mName) {
        this.hashInstance = mHash
    }
}


fun main() {
    val user = User("#123", "chris")
    println("${user.id}, ${user.name}")
}