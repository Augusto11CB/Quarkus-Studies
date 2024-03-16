package com.buenosdev.infrastructure.checkhealth

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.health.HealthCheck
import org.eclipse.microprofile.health.HealthCheckResponse
import org.eclipse.microprofile.health.Readiness

@Readiness
@ApplicationScoped
class ReadinessHealthCheck : HealthCheck {

    override fun call(): HealthCheckResponse {
        return HealthCheckResponse.up("Readiness health check")
    }
}
