package com.kakaoent.md.domain.upload

import com.kakaoent.md.domain.AuditingEntity
import com.kakaoent.md.domain.DOMAIN_ID_LENGTH
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "upload_file")
class UploadFile(
    @Column(nullable = false, updatable = false, unique = true, length = DOMAIN_ID_LENGTH)
    val uploadFileId: String,

    @Column(nullable = false, updatable = false, length = 255)
    val path: String,

    @Column(nullable = false, updatable = false, length = 100)
    val name: String,

    @Column(nullable = false, updatable = false)
    val size: Long,

    @Column(nullable = false, updatable = false, length = 50)
    val contentType: String,

    @Column(nullable = false)
    val uploadAt: Instant,

    status: UploadFileStatus = UploadFileStatus.INACTIVE
) : AuditingEntity() {

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    var status: UploadFileStatus = status
        protected set

    fun activate() {
        status = UploadFileStatus.ACTIVE
    }

    fun deactivate() {
        status = UploadFileStatus.INACTIVE
    }
}

enum class UploadFileStatus {
    ACTIVE,
    INACTIVE,
}
