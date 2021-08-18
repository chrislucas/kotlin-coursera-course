package com.br.week4.oop.clazz.delegation.sample


class ProxyDelegation<T>(private val repository: GenRepository<T>, private val logger: Logger) :
    GenRepository<T> by repository, Logger by logger

class ProxyDelegationRepository<T, R>(
    private val parser: ParseRepository<T, R>
): ParseRepository<T, R> by parser


open class User(val id: Int, val name: String)

class UserSystem(id: Int, name: String, val access: Int): User(id, name)

class MockAdminRepository : ParseRepository<User, UserSystem> {

    private val mockedUser = User(1, "chris")

    private val mockedAdmin = UserSystem(mockedUser.id, mockedUser.name, 0)

    override fun getById(id: Int): User = mockedUser

    override fun getAll(): List<User> = listOf(mockedUser)

    override fun parseValueGotById(value: User): UserSystem = mockedAdmin

    override fun parseAllValues(values: List<User>): List<UserSystem> = listOf(mockedAdmin)
}

fun main() {
    val repository = ProxyDelegationRepository<User, UserSystem>(MockAdminRepository())

    val user = repository.getById(1)
    val users = repository.getAll()

    val sysUser: UserSystem = repository.parseValueGotById(user)
    val sysUsers: List<UserSystem> = repository.parseAllValues(users)

}