package com.kakaoent.md.config.serde

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.time.Instant

class UnixInstantSerializer : JsonSerializer<Instant>() {
    override fun serialize(value: Instant, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeNumber(value.epochSecond)
    }
}

val objectMapper = jsonMapper {
    configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false)

    addModule(kotlinModule())
    addModule(JavaTimeModule().apply {
        addSerializer(Instant::class.java, UnixInstantSerializer())
    })
}

@Configuration
class JsonConfig {

    @Bean
    @Primary
    fun objectMapper() = objectMapper

}
