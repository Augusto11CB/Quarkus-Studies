package com.buenosdev.core.usecase.transaction

import com.buenosdev.core.domain.Transaction
import com.buenosdev.core.port.inbound.transaction.ProcessTransactionPort
import com.buenosdev.core.port.outbound.transaction.persistence.TransactionRepositoryPort
import java.math.BigDecimal
import java.util.UUID

class ProcessTransactionUseCase(
    private val transactionRepositoryPort: TransactionRepositoryPort
) : ProcessTransactionPort {

    override fun execute(value: BigDecimal, status: String, info: String?): Transaction {
        val transaction = Transaction(
            referenceCode = UUID.randomUUID().toString(),
            value = value,
            status = status,
            info = info
        )

        transactionRepositoryPort.persist(transaction)

        return transaction
    }
}
