package com.buenosdev.infrastructure.cache

import com.buenosdev.infrastructure.cache.entity.AugDataEntity
import io.quarkus.redis.datasource.RedisDataSource
import io.quarkus.redis.datasource.string.StringCommands
import jakarta.enterprise.context.ApplicationScoped
import java.util.Optional

@ApplicationScoped
class RedisCacheRepository(
    ds: RedisDataSource
) {
    private val commands: StringCommands<String, AugDataEntity>

    init {
        commands = ds.string(AugDataEntity::class.java)
    }

    operator fun get(key: String): Optional<AugDataEntity> {
        return try {
            Optional.of(commands[key])
        } catch (ex: Exception) {
            Optional.empty()
        }
    }

    operator fun set(key: String, expiry: Long, oAuth: AugDataEntity) {
        commands.setex(key, expiry, oAuth)
    }
}
