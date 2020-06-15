package ru.iesorokin.ordermanager.banker.output.stream.sender

import mu.KotlinLogging
import org.springframework.amqp.core.MessageBuilder
import org.springframework.messaging.Message
import org.springframework.stereotype.Service
import ru.iesorokin.ordermanager.banker.config.MessageQueueSource
import ru.iesorokin.ordermanager.banker.error.SendMessageException
import ru.iesorokin.ordermanager.banker.input.dto.BankerTaskMessage

private val log = KotlinLogging.logger {}

@Service
class BankerStatusSender(
        private val messageQueueSource: MessageQueueSource
){

    /**
     * @throws SendMessageException describing the problem while sending unified message
     */
    fun sendStatusByType(task: BankerTaskMessage?) {
        val message = org.springframework.messaging.support.MessageBuilder
                .withPayload(task)
                .build()
        when (task?.bankerOperationType){
            "HOLD" -> sendHoldingSuccess(message)
            "CONFIRM" -> sendConfirmingSuccess(message)
            "REGISTER" -> sendRegisteringSuccess(message)
            "REFUND" -> sendRefundingSuccess(message)
        }
    }

    private fun sendConfirmingSuccess(message: Message<*>) {
        messageQueueSource.confirmingSuccess().sendOrThrow(message) { e ->
            SendMessageException("Message: $message. Exception: $e")
        }
        log.info { "Message was sent to confirmingSuccess exchange. $message" }
    }
    private fun sendRefundingSuccess(message: Message<*>) {
        messageQueueSource.refundingSuccess().sendOrThrow(message) { e ->
            SendMessageException("Message: $message. Exception: $e")
        }
        log.info { "Message was sent to refundingSuccess exchange. $message" }
    }
    private fun sendRegisteringSuccess(message: Message<*>) {
        messageQueueSource.registeringSuccess().sendOrThrow(message) { e ->
            SendMessageException("Message: $message. Exception: $e")
        }
        log.info { "Message was sent to registeringSuccess exchange. $message" }
    }
    private fun sendHoldingSuccess(message: Message<*>) {
        messageQueueSource.holdingSuccess().sendOrThrow(message) { e ->
            SendMessageException("Message: $message. Exception: $e")
        }
        log.info { "Message was sent to holdingSuccess exchange. $message" }
    }
}