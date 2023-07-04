package com.kakaoent.md.support

import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction
import kotlin.reflect.KProperty1

fun <T, R> sortBy(prop: KProperty1<T, R?>): Sort = Sort.by(prop.name)

fun <T, R> sortBy(direction: Direction, prop: KProperty1<T, R?>): Sort = Sort.by(direction, prop.name)

fun <T, R> descendingBy(prop: KProperty1<T, R?>): Sort = sortBy(Direction.DESC, prop)

fun <T, R> ascendingBy(prop: KProperty1<T, R?>): Sort = sortBy(Direction.ASC, prop)
