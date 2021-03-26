package com.br.sample

fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> =
    this.customers.filter {
            customer ->
        val pair = customer.orders.partition { it.isDelivered }
        pair.first.size < pair.second.size
    }.toSet()