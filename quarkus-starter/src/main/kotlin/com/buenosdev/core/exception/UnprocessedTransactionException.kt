package com.buenosdev.core.exception

open class UnprocessedTransactionException(
    override val message: String?
) : RuntimeException(message)
