package com.kakaoent.md.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc

@Import(HttpEncodingAutoConfiguration::class)
@AutoConfigureRestDocs
abstract class ApiSpec {

    @Autowired
    lateinit var mockMvc: MockMvc
}
