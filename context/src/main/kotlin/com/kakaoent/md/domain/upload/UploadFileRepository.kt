package com.kakaoent.md.domain.upload

interface UploadFileRepository {

    fun save(uploadFile: UploadFile): UploadFile

    fun findByUploadFileId(uploadFileId: String): UploadFile?

    fun findByUploadFileIdIn(uploadFileIds: Set<String>): List<UploadFile>

    fun delete(uploadFile: UploadFile)
}
