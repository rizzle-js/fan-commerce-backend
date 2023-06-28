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
import java.io.File

@Service
internal class GoogleCloudStorageService(
    @Value("\${spring.cloud.gcp.storage.bucket}")
    private val bucketName: String,
) : StorageService {

    @Autowired(required = false)
    private lateinit var storage: Storage

    override fun upload(path: String, file: File): FileMetadata = try {

        val fileName = file.name

        storage.create(
            BlobInfo.newBuilder(bucketName, "$path/$fileName").build(),
            file.readBytes(),
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

    override fun upload(path: String, files: List<File>): List<FileMetadata> = files.map { upload(path, it) }

    override fun delete(path: String): Boolean = try {
        storage.delete(bucketName, path)
    } catch (ex: Exception) {
        throw FileDeleteFailedException("Failed to delete file: $path", ex)
    }
}

