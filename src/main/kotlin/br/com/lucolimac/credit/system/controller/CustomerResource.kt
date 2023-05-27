package br.com.lucolimac.credit.system.controller

import br.com.lucolimac.credit.system.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: br.com.lucolimac.credit.system.dto.request.CustomerDto): ResponseEntity<String> {
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<br.com.lucolimac.credit.system.dto.response.CustomerView> {
        val customer: br.com.lucolimac.credit.system.entity.Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK)
            .body(br.com.lucolimac.credit.system.dto.response.CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun upadateCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody @Valid customerUpdateDto: br.com.lucolimac.credit.system.dto.request.CustomerUpdateDto
    ): ResponseEntity<br.com.lucolimac.credit.system.dto.response.CustomerView> {
        val customer: br.com.lucolimac.credit.system.entity.Customer = this.customerService.findById(id)
        val cutomerToUpdate: br.com.lucolimac.credit.system.entity.Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated: br.com.lucolimac.credit.system.entity.Customer = this.customerService.save(cutomerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(
            br.com.lucolimac.credit.system.dto.response.CustomerView(
                customerUpdated
            )
        )
    }
}