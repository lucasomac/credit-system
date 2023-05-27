package br.com.lucolimac.credit.system.service.impl

import br.com.lucolimac.credit.system.repository.CustomerRepository
import br.com.lucolimac.credit.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) : ICustomerService {
    override fun save(customer: br.com.lucolimac.credit.system.entity.Customer): br.com.lucolimac.credit.system.entity.Customer =
        this.customerRepository.save(customer)

    override fun findById(id: Long): br.com.lucolimac.credit.system.entity.Customer =
        this.customerRepository.findById(id)
            .orElseThrow { throw br.com.lucolimac.credit.system.exception.BusinessException("Id $id not found") }

    override fun delete(id: Long) {
        val customer: br.com.lucolimac.credit.system.entity.Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}