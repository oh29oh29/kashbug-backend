package com.kashbug.kashbugbackend

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.toBasicString(): String =
    this.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))

fun String.toLocalDateTime(): LocalDateTime =
    LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))

fun Collection<String>.joinToStringWithRest(): String = if (this.size == 1) this.first() else this.joinToString(separator = ",")