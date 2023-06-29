package com.kakaoent.md.external.storage.impl

import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import com.kakaoent.md.external.storage.FileDeleteFailedException
import com.kakaoent.md.external.storage.FileMetadata
import com.kakaoent.md.external.storage.FileUploadFailedException
import com.kakaoent.md.external.storage.StorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
internal class GoogleCloudStorageService(
    @Value("\${spring.cloud.gcp.storage.bucket}")
    private val bucketName: String,
) : StorageService {

    @Autowired(required = false)
    private lateinit var storage: Storage

    override fun upload(directory: String, file: MultipartFile): FileMetadata = try {

        val fileName = file.originalFilename ?: file.name

        val path = "$directory/$fileName"

        storage.create(
            BlobInfo.newBuilder(bucketName, path).build(),
            file.bytes,
            Storage.BlobTargetOption.detectContentType()
        ).let {
            FileMetadata(
                name = fileName,
                mediaLink = it.mediaLink,
                path = it.name,
                size = it.size,
                contentType = it.contentType,
                uploadAt = it.createTimeOffsetDateTime.toInstant()
            )
        }
    } catch (ex: Exception) {
        throw FileUploadFailedException("Failed to upload file: ${file.name}", ex)
    }

    //google cloud storage에서 bulk upload 지원 안함
    override fun upload(directory: String, files: List<MultipartFile>): List<FileMetadata> = files.map { upload(directory, it) }

    override fun delete(path: String): Boolean = try {
        storage.delete(bucketName, path)
    } catch (ex: Exception) {
        throw FileDeleteFailedException("Failed to delete file: $path", ex)
    }
}

