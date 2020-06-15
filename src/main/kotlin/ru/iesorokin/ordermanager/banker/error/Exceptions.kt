package ru.iesorokin.ordermanager.banker.error

class BankResponseException(override val message: String) : RuntimeException()

class SendMessageException(override val message: String) : RuntimeException()
