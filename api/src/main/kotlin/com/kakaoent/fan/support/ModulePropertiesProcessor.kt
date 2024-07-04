package com.kakaoent.fan.support

import org.springframework.boot.SpringApplication
import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.boot.env.YamlPropertySourceLoader
import org.springframework.core.Ordered
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver

class ModulePropertiesProcessor : EnvironmentPostProcessor, Ordered {

    override fun postProcessEnvironment(environment: ConfigurableEnvironment?, application: SpringApplication?) {
        val loader = YamlPropertySourceLoader()
        val classPathResources = PathMatchingResourcePatternResolver().getResources("classpath*:application-*.yaml")
        classPathResources.flatMap {
            loader.load("module-properties", it)
        }.map {
            MapPropertySource(it.name, it.source as Map<String, Any?>)
        }.forEach {
            environment?.propertySources?.addLast(it)
        }
    }

    override fun getOrder(): Int = ConfigDataEnvironmentPostProcessor.ORDER + 1
}
