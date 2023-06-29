package com.kakaoent.md.admin.application.api.upload

import com.kakaoent.md.admin.application.api.ApiSpec
import com.kakaoent.md.admin.application.api.upload.FileUploadController.Companion.UPLOAD_FILE
import com.kakaoent.md.docs.andDocument
import com.kakaoent.md.docs.requestParts
import com.kakaoent.md.docs.responseBody
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart
import org.springframework.restdocs.payload.JsonFieldType.*


@WebMvcTest(FileUploadController::class)
class FileUploadApiSpec : ApiSpec() {

    @MockkBean
    private lateinit var fileUploadController: FileUploadController

    init {

        test("파일을 업로드하다") {

            val files = listOf(
                MockMultipartFile("files", "image1.png", "image/png", "image".toByteArray()),
            )

            every { fileUploadController.uploadFile(any()) } returns UploadFilesResponse(
                files = listOf(
                    UploadFileResponse(
                        fileId = FILE_ID,
                        fileName = "test1.png",
                        fileSize = 5,
                        filePath = "https://dev-fan-static.melon.com/md/2023/6/30/image1.png",
                        fileType = "image/png"
                    ),
                )
            )

            mockMvc.perform(
                multipart(UPLOAD_FILE)
                    .file(files[0])
            ).andDocument(
                "FileUploadAdminApiSpec 파일 업로드",
                requestParts {
                    "files" means "업로드할 파일 목록"
                },
                responseBody {
                    "files" type ARRAY means "업로드된 파일 목록"
                    "files[].fileId" type STRING means "파일 ID"
                    "files[].fileName" type STRING means "파일 이름"
                    "files[].fileSize" type NUMBER means "파일 크기"
                    "files[].filePath" type STRING means "파일 경로"
                    "files[].fileType" type STRING means "파일 타입"
                }
            )
        }
    }

    companion object {
        private const val FILE_ID = "6Pw8nWgZhiA8T9NrejVVpW"
    }
}
