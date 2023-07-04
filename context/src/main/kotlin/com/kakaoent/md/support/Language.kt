package com.kakaoent.md.support

enum class Language(val value: String) {
    KOREAN("ko"),
    ENGLISH("en"),
    JAPANESE("ja"),
    CHINESE("zh");

    companion object {
        fun of(value: String): Language {
            return values().find { it.value == value || it.value == value.lowercase() }
                ?: throw IllegalArgumentException("Invalid Language: $value")
        }
    }
}
