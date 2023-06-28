package com.kakaoent.md.domain.code.impl

import com.kakaoent.md.domain.code.CodeCategoryDomainService
import com.kakaoent.md.domain.code.CodeCategoryRepository
import org.springframework.stereotype.Service

internal class CodeCategoryDomainServiceImpl(
    private val repository: CodeCategoryRepository
) : CodeCategoryDomainService {

}
