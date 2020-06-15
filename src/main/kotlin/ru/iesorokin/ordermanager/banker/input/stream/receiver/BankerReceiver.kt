package ru.iesorokin.ordermanager.banker.input.stream.receiver

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import ru.iesorokin.ordermanager.banker.config.START_MONEY_CONFIRMING
import ru.iesorokin.ordermanager.banker.config.START_MONEY_HOLDING
import ru.iesorokin.ordermanager.banker.config.START_MONEY_REFUNDING
import ru.iesorokin.ordermanager.banker.config.START_MONEY_REGISTERING
import ru.iesorokin.ordermanager.banker.core.service.bank.BankerService
import ru.iesorokin.ordermanager.banker.input.dto.BankerTaskMessage

private val log = KotlinLogging.logger {}

@Service
class BankerReceiver(
        private val bankerService: BankerService,
        @Value("\${banker.consumer.default.maxRetry}")
        private val maxRetry: Long
) {
    @StreamListener(START_MONEY_CONFIRMING)
    fun confirming(
            @Payload message: BankerTaskMessage,
            @Header(name = X_DEATH_HEADER, required = false) death: Map<Any, Any?>?
    ) {
        log.inputMessage(START_MONEY_CONFIRMING, message)
        if (death.isDeadLetterCountOverflown(maxRetry)) {
            log.deadLetterCountOverflownError(maxRetry, START_MONEY_CONFIRMING, message)
            return
        }

        bankerService.confirm(message)
    }
    @StreamListener(START_MONEY_REGISTERING)
    fun registering(
            @Payload message: BankerTaskMessage,
            @Header(name = X_DEATH_HEADER, required = false) death: Map<Any, Any?>?
    ) {
        log.inputMessage(START_MONEY_REGISTERING, message)
        if (death.isDeadLetterCountOverflown(maxRetry)) {
            log.deadLetterCountOverflownError(maxRetry, START_MONEY_REGISTERING, message)
            return
        }

        bankerService.register(message)
    }
    @StreamListener(START_MONEY_HOLDING)
    fun holding(
            @Payload message: BankerTaskMessage,
            @Header(name = X_DEATH_HEADER, required = false) death: Map<Any, Any?>?
    ) {
        log.inputMessage(START_MONEY_HOLDING, message)
        if (death.isDeadLetterCountOverflown(maxRetry)) {
            log.deadLetterCountOverflownError(maxRetry, START_MONEY_HOLDING, message)
            return
        }

        bankerService.hold(message)
    }
    @StreamListener(START_MONEY_REFUNDING)
    fun refunding(
            @Payload message: BankerTaskMessage,
            @Header(name = X_DEATH_HEADER, required = false) death: Map<Any, Any?>?
    ) {
        log.inputMessage(START_MONEY_REFUNDING, message)
        if (death.isDeadLetterCountOverflown(maxRetry)) {
            log.deadLetterCountOverflownError(maxRetry, START_MONEY_REFUNDING, message)
            return
        }

        bankerService.refund(message)
    }
}
