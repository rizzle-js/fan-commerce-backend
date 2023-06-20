package com.kakaoent.md.admin.application.api.customerservice

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class CustomerServiceAdminController {
    @PutMapping(UPDATE_INQUIRY_CATEGORY)
    fun updateInquiryCategory(
        @PathVariable categoryId: String,
        @RequestBody inquiryCategoryUpdateRequest: UpdateInquiryCategoryRequest
    ): UpdateInquiryCategoryResponse {
        // 여기서는 간단하게 임의의 수정 시각을 반환하도록 하겠습니다.
        // 실제로는 이 부분에 카테고리 수정 처리 로직이 들어가야 합니다.
        return UpdateInquiryCategoryResponse(
            categoryId = categoryId,
            categoryName = inquiryCategoryUpdateRequest.categoryName,
            updatedAt = Instant.ofEpochSecond(1686641320L),
        )
    }

    companion object {
        const val UPDATE_INQUIRY_CATEGORY = "/inquiry-categories/{categoryId}"
    }
}