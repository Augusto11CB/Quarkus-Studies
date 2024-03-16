package com.buenosdev.adapter.inbound.rest

import com.buenosdev.adapter.inbound.rest.request.TransactionRequest
import com.buenosdev.adapter.inbound.rest.response.TransactionResponse
import com.buenosdev.core.port.inbound.transaction.ProcessTransactionPort
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.jboss.logging.Logger

@Path("/v1/transactions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class TransactionResource(
    private val processTransactionPort: ProcessTransactionPort,
) {
    private val LOGGER: Logger = Logger.getLogger(TransactionResource::class.java)

    @Operation(summary = "Init a transaction")
    @APIResponse(
        responseCode = "200-OK",
        description = "Transaction completed",
        content = [
            Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema =
                Schema(
                    implementation = TransactionRequest::class,
                    type = SchemaType.OBJECT
                )
            )
        ]
    )
    @POST
    fun captureTransaction(transactionRequest: TransactionRequest): Response {

        val transaction = processTransactionPort.execute(
            transactionRequest.value,
            transactionRequest.status,
            transactionRequest.info
        )

        return transaction.let {
            Response.ok(
                TransactionResponse(
                    it.referenceCode,
                    it.value,
                    it.status,
                    it.status
                )
            ).build()
        }
    }
}
