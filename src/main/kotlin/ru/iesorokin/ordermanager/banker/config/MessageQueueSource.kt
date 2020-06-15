package ru.iesorokin.ordermanager.banker.config

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.SubscribableChannel

internal const val START_MONEY_REGISTERING = "startMoneyRegistering"
internal const val START_MONEY_HOLDING = "startMoneyHolding"
internal const val START_MONEY_CONFIRMING = "startMoneyConfirming"
internal const val START_MONEY_REFUNDING = "startMoneyRefunding"

internal const val REGISTERING_SUCCESS = "registeringSuccess"
internal const val HOLDING_SUCCESS = "holdingSuccess"
internal const val CONFIRM_SUCCESS = "confirmingSuccess"
internal const val REFUNDING_SUCCESS = "refundingSuccess"

interface MessageQueueSource {
    // Input
    @Input(START_MONEY_REGISTERING)
    fun startMoneyRegistering(): SubscribableChannel

    @Input(START_MONEY_HOLDING)
    fun startMoneyHolding(): SubscribableChannel

    @Input(START_MONEY_CONFIRMING)
    fun startMoneyConfirming(): SubscribableChannel

    @Input(START_MONEY_REFUNDING)
    fun startMoneyRefunding(): SubscribableChannel

    // Output
    @Output(REGISTERING_SUCCESS)
    fun registeringSuccess(): SubscribableChannel

    @Output(HOLDING_SUCCESS)
    fun holdingSuccess(): SubscribableChannel

    @Output(CONFIRM_SUCCESS)
    fun confirmingSuccess(): SubscribableChannel

    @Output(REFUNDING_SUCCESS)
    fun refundingSuccess(): SubscribableChannel
}

