package com.buenosdev.core.port.outbound.transaction.persistence

import com.buenosdev.core.domain.Transaction

interface TransactionRepositoryPort {

    fun findByReferenceCode(referenceCode: String): Transaction?
    fun persist(transaction: Transaction)
    fun update(transaction: Transaction)
    fun updateStatus(referenceCode: String, transactionStatus: String)
}
