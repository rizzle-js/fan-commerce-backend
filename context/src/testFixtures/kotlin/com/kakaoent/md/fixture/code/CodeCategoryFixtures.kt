package com.kakaoent.md.fixture.code

import com.kakaoent.md.UuidGenerator
import com.kakaoent.md.domain.code.Code
import com.kakaoent.md.domain.code.CodeCategory

fun codeCategory(
    codeCategoryId: String = UuidGenerator.generate(),
    name: String = "코드 카테고리명",
    description: String = "코드 카테고리 설명",
    codes: List<Code> = emptyList()
): CodeCategory = CodeCategory(
    codeCategoryId = codeCategoryId,
    name = name,
    description = description,
    codes = codes
)

fun code(
    codeCategory: CodeCategory,
    name: String = "코드명",
    description: String = "코드 설명"
): Code = Code(
    codeCategory = codeCategory,
    name = name,
    description = description
)
