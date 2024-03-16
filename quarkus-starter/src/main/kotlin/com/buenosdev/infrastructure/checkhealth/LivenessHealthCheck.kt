package com.buenosdev.infrastructure.checkhealth

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.health.HealthCheck
import org.eclipse.microprofile.health.HealthCheckResponse
import org.eclipse.microprofile.health.Liveness

@Liveness
@ApplicationScoped
class LivenessHealthCheck : HealthCheck {

    override fun call(): HealthCheckResponse {
        return HealthCheckResponse.up("Liveness health check")
    }
}
