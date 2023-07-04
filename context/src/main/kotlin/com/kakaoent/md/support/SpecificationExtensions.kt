package com.kakaoent.md.support

import com.kakaoent.md.domain.BaseEntity
import jakarta.persistence.criteria.*
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

fun <R> Path<*>.get(prop: KProperty1<*, R?>): Path<R> = get(prop.name)

fun <T, R> Root<T>.get(prop: KProperty1<T, R?>): Path<R> = get(prop.name)

inline fun <reified T : Any> CriteriaQuery<*>.from(t: KClass<T>): Root<T> = from(t.java)

fun <T, R> Root<T>.join(prop: KProperty1<T, R?>): Join<T, R> = join(prop.name)

fun <T, R> KProperty1<T, R?>.equal(x: R): Specification<T> = specification { equal(it, x) }

fun <T> notDeleted(): Specification<T> = where { root -> isFalse(root.get(BaseEntity::deleted)) }

fun <T> KProperty1<T, Boolean?>.isTrue() = specification { isTrue(it) }

fun <T> KProperty1<T, Boolean?>.isFalse() = specification { isFalse(it) }

fun <T> where(predicate: CriteriaBuilder.(Root<T>) -> Predicate): Specification<T> = Specification { root, _, criteriaBuilder ->
    criteriaBuilder.predicate(root)
}

infix fun <T> Specification<T>.and(other: Specification<T>): Specification<T> = and(other)

infix fun <T> Specification<T>.or(other: Specification<T>): Specification<T> = or(other)

inline fun <reified T> empty(): Specification<T> = Specification.where(null)

private fun <T, R> KProperty1<T, R?>.specification(predicate: CriteriaBuilder.(path: Path<R>) -> Predicate): Specification<T> =
    let { property -> where { root -> predicate(root.get(property)) } }
