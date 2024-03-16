package com.buenosdev.core.domain

import java.math.BigDecimal

data class Transaction(
    val referenceCode: String,
    val value: BigDecimal,
    val status: String,
    val info: String?
) {
    constructor(
        referenceCode: String,
        value: BigDecimal,
        status: String
    ) : this(referenceCode, value, status, null)
}
