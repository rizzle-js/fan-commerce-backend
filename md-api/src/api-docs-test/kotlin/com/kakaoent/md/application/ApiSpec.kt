package com.kakaoent.md.application

import com.kakaoent.md.config.JsonConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer
import org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris
import org.springframework.restdocs.templates.TemplateFormats.markdown
import org.springframework.test.web.servlet.MockMvc


@Import(HttpEncodingAutoConfiguration::class, CustomizationConfiguration::class, JsonConfig::class)
@AutoConfigureRestDocs
abstract class ApiSpec {

    @Autowired
    lateinit var mockMvc: MockMvc
}


@TestConfiguration
internal class CustomizationConfiguration : RestDocsMockMvcConfigurationCustomizer {
    override fun customize(configurer: MockMvcRestDocumentationConfigurer) {
        val uriPreprocessor = modifyUris()
            .scheme("https")
            .host("md.melon.com")
            .removePort()

        configurer.operationPreprocessors().apply {
            withRequestDefaults(uriPreprocessor)
            withResponseDefaults(uriPreprocessor)
        }

        configurer.snippets().withTemplateFormat(markdown())
    }
}
