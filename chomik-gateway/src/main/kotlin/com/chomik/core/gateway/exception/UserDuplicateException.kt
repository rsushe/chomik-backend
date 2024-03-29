package com.chomik.core.gateway.exception

import java.lang.RuntimeException

class UserDuplicateException(message: String, cause: Throwable): RuntimeException(message, cause)
