package com.github.diogocerqueiralima.emailservice.consumers

import com.github.diogocerqueiralima.emailservice.dto.EmailDto
import com.github.diogocerqueiralima.emailservice.services.EmailService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class EmailConsumer(

    private val emailService: EmailService

) {

    @RabbitListener(queues = ["\${broker.queue.email.name}"])
    fun emailQueueListener(@Payload dto: EmailDto) {

        emailService.send(
            to = dto.email,
            subject = dto.subject,
            template = dto.template,
            variables = dto.variables
        )

    }

}