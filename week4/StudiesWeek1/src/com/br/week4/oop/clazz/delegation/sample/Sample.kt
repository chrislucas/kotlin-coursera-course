package com.br.week4.oop.clazz.delegation.sample


class Customer

interface Repository {
    fun getById(id: Int): Customer
    fun getAll(): List<Customer>
}

interface GenRepository<T> {
    fun getById(id: Int): T
    fun getAll(): List<T>
}

interface Logger {
    fun logAll()
}

class ControllerProxy<T>(private val repository: GenRepository<T>, private val logger: Logger) : GenRepository<T>,
    Logger {
    override fun getById(id: Int): T =
        repository.getById(id)

    override fun getAll(): List<T> = repository.getAll()

    override fun logAll() = logger.logAll()
}

interface ParseRepository<T, R> : GenRepository<T> {
    fun parseValueGotById(value: T): R
    fun parseAllValues(values: List<T>): List<R>
}

class ProxyTransformer<T, R>(private val repo: GenRepository<T>, private val parse: ParseRepository<T, R>) :
    GenRepository<R> {
    override fun getById(id: Int): R = parse.parseValueGotById(repo.getById(id))

    override fun getAll(): List<R> = parse.parseAllValues(repo.getAll())

}