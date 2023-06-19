package com.kakaoent.md.admin

import com.fasterxml.jackson.module.kotlin.readValue
import com.kakaoent.md.config.objectMapper
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.ResultActionsDsl

internal object IntegrationTestConfig : AbstractProjectConfig() {

    override val parallelism = 3

    /**
     * 테스트 시나리오마다 새로운 인스턴스를 생성한다.
     */
    override val isolationMode = IsolationMode.InstancePerLeaf

    override fun extensions(): List<Extension> = listOf(SpringExtension)

}

@SpringBootTest
@AutoConfigureMockMvc
abstract class IntegrationTestSpec(body: FeatureSpec.() -> Unit) : FeatureSpec(body)


inline fun <reified T> ResultActions.responseBody(): T = objectMapper.readValue(
    andReturn().response.contentAsByteArray
)

inline fun <reified T> ResultActionsDsl.responseBody(): T = objectMapper.readValue(
    andReturn().response.contentAsByteArray
)