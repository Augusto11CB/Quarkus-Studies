package com.buenosdev.adapter.outbound.aws.kms

import io.quarkus.cache.CacheInvalidate
import io.quarkus.cache.CacheResult
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.faulttolerance.Retry
import org.eclipse.microprofile.faulttolerance.Timeout
import org.jboss.logging.Logger
import software.amazon.awssdk.core.SdkBytes
import software.amazon.awssdk.services.kms.KmsClient
import java.time.temporal.ChronoUnit
import java.util.Base64

@ApplicationScoped
class KeyManagementServiceAdapter(
    private val kmsClient: KmsClient,
    @ConfigProperty(name = "aws.kms.key.arn") private val keyArn: String
) { // : EncryptorPort, DecryptorPort {
    private val LOGGER: Logger = Logger.getLogger(KeyManagementServiceAdapter::class.java)

    @Retry(maxRetries = 2)
    @Timeout(value = 500, unit = ChronoUnit.MILLIS)
    @CacheInvalidate(cacheName = "app-cache")
    fun encrypt(plainText: String): String {
        LOGGER.info("I=invoke kms encrypt")

        return kmsClient.encrypt {
            it.keyId(keyArn)
            it.plaintext(SdkBytes.fromUtf8String(plainText))
        }.ciphertextBlob().let {
            Base64.getEncoder().encodeToString(it.asByteArray())
        }
    }

    @Retry(maxRetries = 2)
    @Timeout(value = 500, unit = ChronoUnit.MILLIS)
    @CacheResult(cacheName = "app-cache")
    fun decrypt(encrypted: String): String {
        LOGGER.info("I=invoke kms decrypt")
        return kmsClient.decrypt {
            it.keyId(keyArn)
            it.ciphertextBlob(SdkBytes.fromByteArray(Base64.getDecoder().decode(encrypted)))
        }.plaintext().asUtf8String()
    }
}
