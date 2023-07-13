package com.kakaoent.md.support

import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction
import org.springframework.data.mapping.toDotPath
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1

fun <U> sortBy(direction: Direction, prop: KProperty<U>): Sort = Sort.by(direction, prop.toDotPath())

fun <U> descendingBy(prop: KProperty<U>): Sort = sortBy(Direction.DESC, prop)

fun <U> ascendingBy(prop: KProperty<U>): Sort = sortBy(Direction.ASC, prop)
