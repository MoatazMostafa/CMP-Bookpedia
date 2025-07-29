package com.plcoding.bookpedia.core.presentation

import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.error_disk_full
import cmp_bookpedia.composeapp.generated.resources.error_no_internet
import cmp_bookpedia.composeapp.generated.resources.error_request_timeout
import cmp_bookpedia.composeapp.generated.resources.error_serialization
import cmp_bookpedia.composeapp.generated.resources.error_too_many_requests
import cmp_bookpedia.composeapp.generated.resources.error_unknown
import com.plcoding.bookpedia.core.domain.DataError

fun DataError.toUiText(): UiText{
    val stringResId = when(this){
        DataError.Local.NOT_FOUND -> Res.string.error_unknown
        DataError.Local.DATABASE -> Res.string.error_unknown
        DataError.Local.DISK_FULL ->  Res.string.error_disk_full
        DataError.Local.UNKNOWN ->  Res.string.error_unknown
        DataError.Remote.BAD_REQUEST ->  Res.string.error_unknown
        DataError.Remote.NETWORK ->  Res.string.error_no_internet
        DataError.Remote.NOT_FOUND ->  Res.string.error_unknown
        DataError.Remote.SERIALIZATION ->  Res.string.error_serialization
        DataError.Remote.SERVER_ERROR -> Res.string.error_unknown
        DataError.Remote.TIMEOUT ->  Res.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS ->  Res.string.error_too_many_requests
        DataError.Remote.UNAUTHORIZED -> Res.string.error_unknown
        DataError.Remote.UNKNOWN -> Res.string.error_unknown
    }
    return UiText.StringResourceId(stringResId)
}