package com.kakaoent.md.admin.application.api.upload

import com.kakaoent.md.UuidGenerator
import com.kakaoent.md.domain.upload.UploadFile
import com.kakaoent.md.domain.upload.UploadFileRepository
import com.kakaoent.md.domain.upload.UploadFileStatus
import com.kakaoent.md.external.storage.StorageService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

@Service
@Transactional
class FileUploadService(
    @Value("\${app.melon.static.host}")
    private val resourceHost: String,
    private val uploadFileRepository: UploadFileRepository,
    private val storageService: StorageService,
) {

    fun uploadFile(
        multipartFiles: List<MultipartFile>,
        localDate: LocalDate = LocalDate.now()
    ): UploadFilesResponse {
        require(multipartFiles.isNotEmpty()) { "파일이 첨부되지 않았습니다." }

        val targetDirectory = "md/${localDate.year}/${localDate.monthValue}/${localDate.dayOfMonth}"

        val uploadFiles = storageService.upload(directory = targetDirectory, files = multipartFiles)
            .map {
                uploadFileRepository.save(
                    UploadFile(
                        uploadFileId = UuidGenerator.generate(),
                        name = it.name,
                        path = "/${it.path}",
                        size = it.size,
                        contentType = it.contentType,
                        uploadAt = it.uploadAt,
                        status = UploadFileStatus.INACTIVE
                    )
                )
            }

        return UploadFilesResponse(
            files = uploadFiles.map {
                UploadFileResponse(
                    fileId = it.uploadFileId,
                    fileName = it.name,
                    filePath = resourceHost + it.path,
                    fileSize = it.size,
                    fileType = it.contentType,
                )
            }
        )
    }
}
