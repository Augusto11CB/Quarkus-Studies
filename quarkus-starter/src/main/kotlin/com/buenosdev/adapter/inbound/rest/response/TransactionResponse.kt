package com.buenosdev.adapter.inbound.rest.response

import java.math.BigDecimal

data class TransactionResponse(
    val referenceCode: String,
    val value: BigDecimal,
    val status: String,
    val info: String?
)
