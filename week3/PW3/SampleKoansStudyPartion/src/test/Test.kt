package test

import com.br.sample.customers
import com.br.sample.getCustomersWithMoreUndeliveredOrdersThanDelivered
import com.br.sample.reka
import com.br.sample.shop
import org.junit.Assert
import org.junit.Test


class TestPartition {
    @Test fun testGetCustomersWhoHaveMoreUndeliveredOrdersThanDelivered() {
        Assert.assertEquals("getCustomerWithMaximumNumberOfOrders",
            setOf(customers[reka]), shop.getCustomersWithMoreUndeliveredOrdersThanDelivered())
    }
}
