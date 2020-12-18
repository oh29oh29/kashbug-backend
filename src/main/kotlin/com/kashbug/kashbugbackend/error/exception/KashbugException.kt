package com.kashbug.kashbugbackend.error.exception

import com.kashbug.kashbugbackend.application.data.ResponseCode

class KashbugException(
    private val code: ResponseCode,
    override val message: String?,
    override val cause: Throwable?
) : RuntimeException(message, cause) {

    constructor(code: ResponseCode) : this(code, code.message, null)

    constructor(code: ResponseCode, cause: Throwable) : this(code, code.message, cause)

}