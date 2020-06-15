
package ru.iesorokin.ordermanager.banker

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.scheduling.annotation.EnableScheduling
import ru.iesorokin.ordermanager.banker.config.MessageQueueSource

@SpringBootApplication(exclude = [RabbitAutoConfiguration::class])
@EnableEurekaClient
@EnableBinding(MessageQueueSource::class)
@EnableScheduling
@EnableSchedulerLock(
        defaultLockAtMostFor = "\${schedule.default.lockAtMost}",
        defaultLockAtLeastFor = "\${schedule.default.lockAtLeast}"
)
class BankerApplication

fun main(args: Array<String>) {
    SpringApplication.run(BankerApplication::class.java, *args)
}