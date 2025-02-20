package com.github.diogocerqueiralima.emailservice.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig(

    @Value("\${broker.queue.email.name}")
    private val queue: String

) {

    @Bean
    fun queue() = Queue(queue, true)

    @Bean
    fun messageConverter(): Jackson2JsonMessageConverter {

        val objectMapper = ObjectMapper()

        return Jackson2JsonMessageConverter(objectMapper)
    }

}