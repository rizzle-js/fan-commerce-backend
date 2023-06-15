package com.kakaoent.md.application.api

import org.springframework.restdocs.cookies.CookieDescriptor
import org.springframework.restdocs.cookies.CookieDocumentation
import org.springframework.restdocs.cookies.CookieDocumentation.cookieWithName
import org.springframework.restdocs.cookies.RequestCookiesSnippet
import org.springframework.restdocs.headers.HeaderDescriptor
import org.springframework.restdocs.headers.HeaderDocumentation.*
import org.springframework.restdocs.headers.RequestHeadersSnippet
import org.springframework.restdocs.headers.ResponseHeadersSnippet
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.payload.RequestFieldsSnippet
import org.springframework.restdocs.payload.ResponseFieldsSnippet
import org.springframework.restdocs.request.*
import org.springframework.restdocs.request.RequestDocumentation.*
import org.springframework.restdocs.snippet.Attributes
import org.springframework.restdocs.snippet.Snippet
import org.springframework.test.web.servlet.*

fun ResultActions.andDocument(
    identifier: String,
    vararg snippet: Snippet
): ResultActions = andDo(
    document(
        identifier,
        preprocessRequest(prettyPrint()),
        preprocessResponse(prettyPrint()),
        *snippet
    )
)

data class Parameter(val descriptor: ParameterDescriptor) {

    infix fun optional(value: Boolean): Parameter {
        if (value) this.descriptor.optional()
        return this
    }
}

class ParameterBuilder {
    private val fields = mutableListOf<ParameterDescriptor>()

    infix fun String.means(description: Any): Parameter {
        val descriptor = parameterWithName(this).description(description)
        val parameter = Parameter(descriptor)
        fields.add(parameter.descriptor)
        return parameter
    }

    fun build() = fields.toList()
}

class PartBuilder {
    private val fields = mutableListOf<RequestPartDescriptor>()

    infix fun String.means(description: Any) {
        fields.add(partWithName(this).description(description))
    }

    fun build() = fields.toList()
}

fun pathVariables(builder: ParameterBuilder.() -> Unit): PathParametersSnippet = pathParameters(
    ParameterBuilder().apply(builder).build()
)

fun queryParams(builder: ParameterBuilder.() -> Unit): QueryParametersSnippet = queryParameters(
    ParameterBuilder().apply(builder).build()
)

fun requestParts(builder: PartBuilder.() -> Unit): RequestPartsSnippet = requestParts(
    PartBuilder().apply(builder).build()
)

class HeaderBuilder {
    private val headers = mutableListOf<HeaderDescriptor>()

    infix fun String.means(value: Any) {
        headers.add(headerWithName(this).description(value))
    }

    fun build() = headers.toList()
}

fun requestHeaders(builder: HeaderBuilder.() -> Unit): RequestHeadersSnippet = requestHeaders(
    HeaderBuilder().apply(builder).build()
)

fun responseHeaders(builder: HeaderBuilder.() -> Unit): ResponseHeadersSnippet = responseHeaders(
    HeaderBuilder().apply(builder).build()
)

class CookieBuilder {
    private val cookies = mutableListOf<CookieDescriptor>()

    infix fun String.means(value: Any) {
        cookies.add(cookieWithName(this).description(value))
    }

    fun build() = cookies.toList()
}

fun requestCookies(builder: CookieBuilder.() -> Unit): RequestCookiesSnippet = CookieDocumentation.requestCookies(
    CookieBuilder().apply(builder).build()
)

class Field(val descriptor: FieldDescriptor) {

    infix fun means(value: String): Field = Field(descriptor.description(value))

    infix fun attributes(block: Field.() -> Unit): Field {
        block()
        return this
    }

    infix fun format(value: String): Field {
        descriptor.attributes(Attributes.Attribute("format", value))
        return this
    }

    infix fun optional(isOptional: Boolean): Field {
        if (isOptional) descriptor.optional()
        return this
    }
}

class FieldsBuilder {
    private val fields = mutableListOf<FieldDescriptor>()

    infix fun String.type(type: JsonFieldType): Field {
        val field = Field(fieldWithPath(this).type(type))
        fields.add(field.descriptor)
        return field
    }

    fun build() = fields.toList()
}

fun requestBody(builder: FieldsBuilder.() -> Unit): RequestFieldsSnippet = requestFields(
    FieldsBuilder().apply(builder).build()
)

fun responseBody(builder: FieldsBuilder.() -> Unit): ResponseFieldsSnippet = responseFields(
    FieldsBuilder().apply(builder).build()
)
