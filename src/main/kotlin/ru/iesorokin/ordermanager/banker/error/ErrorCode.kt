package ru.iesorokin.ordermanager.banker.error

enum class ErrorCode(val code: Int, val errorMessage: String) {
    UNEXPECTED(201, "unexpected.error"),
    INVALID_ATTRIBUTE(103, "invalid.parameter"),
    BANK_NOT_AVAILABLE(400, "BANK_NOT_AVAILABLE"),
    BANK_ERROR(500, "BANK_ERROR")
}
