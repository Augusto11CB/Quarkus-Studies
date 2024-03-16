package com.buenosdev.infrastructure.persistence

import com.buenosdev.infrastructure.persistence.entity.TransactionEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class TransactionPanacheRepository : PanacheRepository<TransactionEntity> {

    fun findByReferenceCode(referenceCode: String): TransactionEntity? {
        return find(
            "select e from TransactionEntity e where e.referenceCode = ?1",
            referenceCode
        ).firstResult()
    }
}
