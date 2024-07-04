package com.kakaoent.fan

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.EnumerablePropertySource
import org.springframework.stereotype.Component

@SpringBootApplication
class FanShopApplication

fun main(args: Array<String>) {
    runApplication<FanShopApplication>(*args)
}

@Component
class FanShopComponent(
    private val configurableEnvironment: ConfigurableEnvironment
) {
    @PostConstruct
    fun init() {
        configurableEnvironment.propertySources.forEach {
            if(it is EnumerablePropertySource<*>) {
                println("Property Source Name: ${it.name}")
                it.propertyNames.forEach { propertyName ->
                    println("     Property Name: $propertyName, Property Value: ${it.getProperty(propertyName)}")
                }
            }
        }
    }
}
