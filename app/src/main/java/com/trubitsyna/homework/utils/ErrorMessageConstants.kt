package com.trubitsyna.homework.utils

object ErrorMessageConstants {
    const val SHORT_USERNAME_MSG = "Username must be longer than 3 characters"
    const val LONG_USERNAME_MSG = "Username must be shorter than 16 characters"
    const val INVALID_CHAR_USERNAME_MSG =
        ("Username must contains characters: \"a\"-\"z\" \"_\" \".\"")
    const val SHORT_PASSWORD_MSG = "Password must be longer than 8 characters"
}