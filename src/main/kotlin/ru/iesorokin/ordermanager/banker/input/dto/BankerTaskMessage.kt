package ru.iesorokin.ordermanager.banker.input.dto

import java.time.LocalDateTime

data class BankerTaskMessage(
        val bankerOperationType: String,
        val correlationId: String,
        val creationDate: LocalDateTime? = null,
        val context: Map<Any, Any>
)