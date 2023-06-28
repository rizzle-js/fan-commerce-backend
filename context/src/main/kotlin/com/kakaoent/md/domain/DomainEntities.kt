package com.kakaoent.md.domain

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.*

const val DOMAIN_ID_LENGTH = 30

@MappedSuperclass
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = -1,
) : Persistable<Long> {

    @Transient
    private var _isNew = true

    override fun getId(): Long = id

    override fun isNew(): Boolean = _isNew

    @Column(name = "deleted", nullable = false)
    var deleted: Boolean = false
        protected set

    open fun delete() {
        deleted = true
    }

    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }
}

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditingEntity : BaseEntity() {

    @Column(name = "created_dt", updatable = false)
    protected lateinit var createdAt: Instant

    @Column(name = "updated_dt")
    protected lateinit var updatedAt: Instant

    @CreatedBy
    @Column(name = "created_by", updatable = false, length = 30)
    protected lateinit var createdBy: String

    @LastModifiedBy
    @Column(name = "updated_by", length = 30)
    protected lateinit var updatedBy: String

    @PrePersist
    protected fun prePersist() {
        if (::createdAt.isInitialized) return
        createdAt = Instant.now()
        updatedAt = Instant.now()
    }

    @PreUpdate
    protected fun preUpdate() {
        updatedAt = Instant.now()
    }
}

const val EXCLUDE_DELETION = "deleted = false"
