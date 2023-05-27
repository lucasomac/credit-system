package br.com.lucolimac.credit.system.service

import br.com.lucolimac.credit.system.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long)
}