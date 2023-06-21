package com.kakaoent.md.admin.application.api.customerservice

import org.springframework.web.bind.annotation.*

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
        )
    }

    @PostMapping(REGISTER_INQUIRY_CATEGORY)
    fun registerInquiryCategory(
        @RequestBody registerInquiryCategoryRequest: RegisterInquiryCategoryRequest
    ): RegisterInquiryCategoryResponse {
        // 여기서는 간단하게 임의의 카테고리 ID와 현재 시각을 반환하도록 하겠습니다.
        // 실제로는 이 부분에 카테고리 등록 처리 로직이 들어가야 합니다.
        return RegisterInquiryCategoryResponse(
            categoryId = "5WtaxHmN4h6mqFh7BWpXe6",
            categoryName = registerInquiryCategoryRequest.categoryName,
        )
    }

    @DeleteMapping(DELETE_INQUIRY_CATEGORY)
    fun deleteInquiryCategory(
        @PathVariable categoryId: String,
    ): DeleteInquiryCategoryResponse {
        // 여기서는 간단하게 삭제 요청 받은 카테고리 ID와 현재 시각을 반환하도록 하겠습니다.
        // 실제로는 이 부분에 카테고리 삭제 처리 로직이 들어가야 합니다.
        return DeleteInquiryCategoryResponse(
            categoryId = categoryId
        )
    }

    companion object {
        const val UPDATE_INQUIRY_CATEGORY = "/inquiry-categories/{categoryId}"
        const val REGISTER_INQUIRY_CATEGORY = "/inquiry-categories"
        const val DELETE_INQUIRY_CATEGORY = "/inquiry-categories/{categoryId}"
    }
}