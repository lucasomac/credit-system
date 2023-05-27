package br.com.lucolimac.credit.system.exception

data class BusinessException(override val message: String?) : RuntimeException(message)