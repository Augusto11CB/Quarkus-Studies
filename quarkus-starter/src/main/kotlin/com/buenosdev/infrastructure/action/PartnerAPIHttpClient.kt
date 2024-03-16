package com.buenosdev.infrastructure.action

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.HeaderParam
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "partner-config")
interface PartnerAPIHttpClient {

    @POST
    @Path("destroy")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun doPost(
        @HeaderParam(value = "client_id") clientId: String,
        @HeaderParam(value = "access_token") accessToken: String
    ): Response

    @POST
    @Path("/access-token")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun generateToken(
        @HeaderParam(value = "client_id") clientId: String,
        @HeaderParam(value = "Authorization") authorization: String
    ): Response
}
