package com.kakaoent.md.external.storage

import org.springframework.web.multipart.MultipartFile
import java.time.Instant
import kotlin.jvm.Throws

interface StorageService {

    @Throws(FileUploadFailedException::class)
    fun upload(directory: String, file: MultipartFile): FileMetadata

    @Throws(FileUploadFailedException::class)
    fun upload(directory: String, files: List<MultipartFile>): List<FileMetadata>

    @Throws(FileDeleteFailedException::class)
    fun delete(path: String): Boolean
}

data class FileMetadata(
    val name: String,
    val mediaLink: String,
    val path: String,
    val size: Long,
    val contentType: String,
    val uploadAt: Instant,
) {
    val extension: String
        get() = name.substringAfterLast('.', "")
}

class FileUploadFailedException(message: String, cause: Throwable?) : RuntimeException(message, cause)
class FileDeleteFailedException(message: String, cause: Throwable?) : RuntimeException(message, cause)