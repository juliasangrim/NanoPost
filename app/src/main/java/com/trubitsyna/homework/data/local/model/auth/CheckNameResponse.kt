package com.trubitsyna.homework.data.local.model.auth

enum class CheckNameResponse(val message: String) {
    SHORT_NAME("Username must be longer than 3 characters"),
    LONG_NAME("Username must be shorter than 16 characters"),
    INVALID_CHAR_NAME("Username must contains characters: \"a\"-\"z\" \"_\" \".\""),
    TAKEN(""),
    FREE("")
}