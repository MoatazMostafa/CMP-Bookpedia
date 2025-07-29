package com.plcoding.bookpedia.core.domain

sealed interface DataError : Error {
    enum class Remote : DataError {
        SERVER_ERROR,
        NETWORK,
        TIMEOUT,
        TOO_MANY_REQUESTS,
        BAD_REQUEST,
        UNAUTHORIZED,
        NOT_FOUND,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local : DataError {
        DISK_FULL,
        DATABASE,
        NOT_FOUND,
        UNKNOWN
    }
}