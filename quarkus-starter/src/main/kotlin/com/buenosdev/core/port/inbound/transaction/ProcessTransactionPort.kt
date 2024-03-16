package com.buenosdev.core.port.inbound.transaction

import com.buenosdev.core.domain.Transaction
import java.math.BigDecimal

interface ProcessTransactionPort {

    fun execute(value: BigDecimal, status: String, info: String?): Transaction
}
