package ru.iesorokin.ordermanager.banker.core.scheduler

import mu.KotlinLogging
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.iesorokin.ordermanager.banker.core.repository.BankTaskFailedRepository
import ru.iesorokin.ordermanager.banker.core.service.bank.BankerService
import ru.iesorokin.ordermanager.banker.input.dto.BankerTaskMessage
import java.time.LocalDateTime

private val log = KotlinLogging.logger {}

@Service
@RefreshScope
class BankTaskScheduler(
        private val bankTaskFailedRepository: BankTaskFailedRepository,
        private val bankerService: BankerService,
        @param:Value("\${schedule.banker.minutesForSelfProcessing}")
        private val minutesForSelfProcessing: Long
) {
    @SchedulerLock(
            name = "banker",
            lockAtLeastFor = "\${schedule.banker.lockAtLeast}",
            lockAtMostFor = "\${schedule.banker.lockAtMost}"
    )
    @Scheduled(cron = "\${schedule.banker.cron}", zone = "UTC")
    fun schedule() {
        val failedMessages = bankTaskFailedRepository.findAll()

        if (failedMessages.isNotEmpty()) {
            notify(failedMessages)

            failedMessages.forEach {
                bankerService.process(it)
            }
        }
    }

    private fun notify(failedMessages: List<BankerTaskMessage>) {
        log.info { "Start scheduling for bank tasks: $failedMessages" }

        val oldMessages = failedMessages.filter {
            it.creationDate!!.isBefore(LocalDateTime.now().minusMinutes(minutesForSelfProcessing))
        }
        if (oldMessages.isNotEmpty()) {
            log.error { "Some tasks can't be processed too long. Messages: $oldMessages" }
        }
    }
}