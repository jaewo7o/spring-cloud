package com.jaewoo.cloud.api.error.exception

import java.lang.RuntimeException

class InvalidInputException : RuntimeException {
    constructor(message:String) : super(message)
    constructor(message:String, cause:Throwable) : super(message, cause)
    constructor(cause:Throwable) : super(cause)
}