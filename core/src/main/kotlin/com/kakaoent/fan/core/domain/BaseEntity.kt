package com.kakaoent.fan.core.domain

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import jakarta.persistence.Transient
import org.springframework.data.domain.Persistable

@MappedSuperclass
abstract class BaseEntity<T>(
    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "BIGINT comment '식별자'")
    private val id: T
) : Persistable<T> {

    @Transient
    private var _isNew = true

    override fun isNew(): Boolean = _isNew

    override fun getId(): T = id

    @PostPersist
    @PostLoad
    fun persisted() {
        _isNew = false
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseEntity<*>) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}

val <T> Collection<BaseEntity<T>>.ids: List<T> get() = map { it.id }
