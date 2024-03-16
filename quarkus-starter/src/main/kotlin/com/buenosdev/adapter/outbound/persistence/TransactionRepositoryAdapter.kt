package com.buenosdev.adapter.outbound.persistence

import com.buenosdev.core.domain.Transaction
import com.buenosdev.core.port.outbound.transaction.persistence.TransactionRepositoryPort
import com.buenosdev.infrastructure.persistence.TransactionPanacheRepository
import com.buenosdev.infrastructure.persistence.entity.TransactionEntity
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.jboss.logging.Logger
import java.time.OffsetDateTime
import java.time.ZonedDateTime

// import javax.enterprise.context.ApplicationScoped
// import javax.transaction.Transactional

@ApplicationScoped
class TransactionRepositoryAdapter(
    private val repository: TransactionPanacheRepository
) : TransactionRepositoryPort {
    companion object {
        private val LOGGER = Logger.getLogger(
            TransactionRepositoryAdapter::class.java
        )
    }

    override fun findByReferenceCode(referenceCode: String): Transaction? {
        return repository.findByReferenceCode(referenceCode)?.run {
            Transaction(
                referenceCode = this.referenceCode,
                status = this.status,
                value = this.value,
                info = this.info
            )
        }
    }

    @Transactional
    override fun persist(transaction: Transaction) {
        val transsationEntity = TransactionEntity().apply {
            createdAt = ZonedDateTime.now()
            updatedAt = OffsetDateTime.now()
            value = transaction.value
            status = transaction.status
            referenceCode = transaction.referenceCode
            info = transaction.info
        }
        repository.persist(transsationEntity)
    }

    @Transactional
    override fun update(transaction: Transaction) {
        repository.findByReferenceCode(transaction.referenceCode)?.run {
            referenceCode = transaction.referenceCode
            status = transaction.status
            value = transaction.value
            info = transaction.info
            repository.persist(this)
        }
    }

    @Transactional
    override fun updateStatus(referenceCode: String, transactionStatus: String) {
        repository.findByReferenceCode(referenceCode)?.run {
            status = transactionStatus
            repository.persist(this)
        }
    }

}
