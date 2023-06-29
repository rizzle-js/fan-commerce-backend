package com.kakaoent.md.domain.upload.impl

import com.kakaoent.md.domain.upload.UploadFile
import com.kakaoent.md.domain.upload.UploadFileRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

internal interface UploadFileCrudRepository : CrudRepository<UploadFile, Long> {

    fun findByUploadFileId(uploadFileId: String): Optional<UploadFile>

    @Query("SELECT uf FROM UploadFile uf WHERE uf.uploadFileId IN :uploadFileIds")
    fun findByUploadFileIdIn(uploadFileIds: Set<String>): List<UploadFile>
}


@Repository
internal class UploadFileRepositoryImpl(
    private val repository: UploadFileCrudRepository
) : UploadFileRepository {

    override fun save(uploadFile: UploadFile): UploadFile = repository.save(uploadFile)

    override fun findByUploadFileId(uploadFileId: String): UploadFile? = repository.findByUploadFileId(uploadFileId).orElse(null)

    override fun findByUploadFileIdIn(uploadFileIds: Set<String>): List<UploadFile> = repository.findByUploadFileIdIn(uploadFileIds)

    override fun delete(uploadFile: UploadFile) {
        repository.delete(uploadFile)
    }
}
