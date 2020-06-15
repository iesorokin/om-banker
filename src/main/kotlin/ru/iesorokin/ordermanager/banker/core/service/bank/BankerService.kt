package ru.iesorokin.ordermanager.banker.core.service.bank

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.iesorokin.ordermanager.banker.core.repository.BankTaskFailedRepository
import ru.iesorokin.ordermanager.banker.input.dto.BankerTaskMessage
import ru.iesorokin.ordermanager.banker.output.stream.sender.BankerStatusSender

private val log = KotlinLogging.logger {}

@Service
class BankerService(
        private val bankTaskFailedRepository: BankTaskFailedRepository,
        private val bankerStatusSender: BankerStatusSender
) {

    fun confirm(message: BankerTaskMessage) {
        bankTaskFailedRepository.save(message)
    }

    fun hold(message: BankerTaskMessage) {
        bankTaskFailedRepository.save(message)
    }

    fun register(message: BankerTaskMessage) {
        bankTaskFailedRepository.save(message)
    }

    fun refund(message: BankerTaskMessage) {
        bankTaskFailedRepository.save(message)
    }

    fun process(task: BankerTaskMessage?) {
        bankerStatusSender.sendStatusByType(task)
    }
}
