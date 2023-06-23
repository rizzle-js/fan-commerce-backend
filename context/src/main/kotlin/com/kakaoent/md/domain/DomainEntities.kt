package com.kakaoent.md.domain

import jakarta.persistence.*
import org.hibernate.annotations.Where
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import java.lang.annotation.Inherited
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
    private fun prePersist() {
        if (::createdAt.isInitialized) return
        createdAt = Instant.now()
        updatedAt = Instant.now()
    }

    @PreUpdate
    private fun preUpdate() {
        updatedAt = Instant.now()
    }
}

@NoRepositoryBean
interface BaseRepository<T : BaseEntity, ID : Any> : CrudRepository<T, ID> {

    @Query("select t from #{#entityName} t where t.deleted = false")
    override fun findById(id: ID): Optional<T>

    @Query("update #{#entityName} t set t.deleted = true where t = :entity")
    @Modifying
    override fun delete(entity: T)
}

const val EXCLUDE_DELETION = "deleted = false"
