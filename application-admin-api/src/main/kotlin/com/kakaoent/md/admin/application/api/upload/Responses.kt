package com.kakaoent.md.admin.application.api.upload

import com.kakaoent.md.domain.upload.UploadFile

data class UploadFilesResponse(
    val files: List<UploadFileResponse>
)

data class UploadFileResponse(
    val fileId: String,
    val fileName: String,
    val filePath: String,
    val fileSize: Long,
    val fileType: String,
) {
    companion object {
        fun from(uploadFile: UploadFile): UploadFileResponse = UploadFileResponse(
            fileId = uploadFile.uploadFileId,
            fileName = uploadFile.name,
            filePath = uploadFile.path,
            fileSize = uploadFile.size,
            fileType = uploadFile.contentType,
        )
    }
}
