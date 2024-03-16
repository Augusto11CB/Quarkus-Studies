package com.buenosdev.infrastructure.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.time.ZonedDateTime

@Entity
@Table(name = "transaction")
@SequenceGenerator(sequenceName = "SQ_TRAN_IDT", allocationSize = 1, name = "SQ_TRAN_IDT")
class TransactionEntity {

    @Id
    @Column(name = "IDT_TRANSACTION")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TRAN_IDT")
    var id: Long? = null

    @Column(name = "COD_REFERENCE")
    lateinit var referenceCode: String

    @Column(name = "IND_STATUS")
    lateinit var status: String

    @Column(name = "DES_INFO")
    var info: String? = null

    @Column(name = "NUM_VALUE")
    lateinit var value: BigDecimal

    @Column(name = "DAT_CREATION")
    var createdAt: ZonedDateTime? = null

    @Column(name = "DAT_UPDATE")
    var updatedAt: OffsetDateTime? = null
}
