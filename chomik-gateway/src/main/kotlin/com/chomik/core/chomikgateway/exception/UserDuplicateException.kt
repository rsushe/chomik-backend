package com.chomik.core.chomikgateway.exception

import java.lang.RuntimeException

class UserDuplicateException(message: String, cause: Throwable): RuntimeException(message, cause)
