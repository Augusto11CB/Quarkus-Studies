package com.buenosdev.adapter.inbound.rest.request

import java.math.BigDecimal

class TransactionRequest(
    val value: BigDecimal,
    val status: String,
    val info: String?

)
