package com.github.diogocerqueiralima.authorizationserver.producers

import com.github.diogocerqueiralima.authorizationserver.domain.User
import com.github.diogocerqueiralima.authorizationserver.dto.EmailDto
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class UserProducer(

    private val rabbitTemplate: RabbitTemplate,

    @Value("\${broker.queue.email.name}")
    private val routingKey: String

) {

    fun publishEmail(user: User) {

        rabbitTemplate.convertAndSend(
            "",
            routingKey,
            EmailDto(
                user.email,
                subject = "Reset password",
                template = "RESET_PASSWORD_TEMPLATE",
                variables = mapOf(
                    "username" to user.username,
                    "url" to "http://localhost:8080/auth/reset?token=${user.token.toString()}"
                )
            )
        )

    }

}