package com.buenosdev.adapter.outbound.redis

import com.buenosdev.adapter.outbound.redis.model.AugDataResponse
import com.buenosdev.core.domain.AugData
import com.buenosdev.infrastructure.cache.RedisCacheRepository
import com.buenosdev.infrastructure.cache.entity.AugDataEntity
import jakarta.enterprise.context.ApplicationScoped
import org.jboss.logging.Logger
import java.time.Duration
import java.time.ZonedDateTime
import java.util.Optional

@ApplicationScoped
class ElasticCacheManagementServiceAdapter(
    private val redisCache: RedisCacheRepository
) { // : ElasticCacheManagementPort {
    val indexRedis = "aug_data"

    companion object {
        private val LOGGER: Logger = Logger.getLogger(ElasticCacheManagementServiceAdapter::class.java)
    }

    fun findAugData(): Optional<AugDataResponse> {
        val dataCacheRequest = redisCache[indexRedis]

        if (dataCacheRequest.isPresent) {
            return Optional.of(AugDataResponse())
        }

        return Optional.empty()
    }

    fun persist(data: AugData): Optional<AugDataResponse> {

        val duration = Duration.between(
            ZonedDateTime.now(), ZonedDateTime.now().plusDays(1)
        )

        val augData = AugDataEntity()

        redisCache[indexRedis, duration.seconds] = augData

        return findAugData()
    }
}
