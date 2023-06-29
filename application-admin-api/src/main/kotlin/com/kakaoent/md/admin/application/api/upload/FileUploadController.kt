package com.kakaoent.md.admin.application.api.upload

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class FileUploadController(
    private val fileUploadService: FileUploadService
) {

    @PostMapping(UPLOAD_FILE)
    fun uploadFile(
        @RequestParam("files") files: List<MultipartFile>
    ): UploadFilesResponse = fileUploadService.uploadFile(files)

    companion object {
        const val UPLOAD_FILE = "/upload"
    }
}
