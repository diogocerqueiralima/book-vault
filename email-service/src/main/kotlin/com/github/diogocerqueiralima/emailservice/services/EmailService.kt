package com.github.diogocerqueiralima.emailservice.services

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Service
class EmailService(

    private val mailSender: JavaMailSender,
    private val templateEngine: TemplateEngine

) {

    fun send(to: String, subject: String, template: String, variables: Map<String, Any>) {

        val context = Context()

        variables.forEach { context.setVariable(it.key, it.value) }

        val mimeMessage = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(mimeMessage, true)
        val htmlContent = templateEngine.process(template, context)

        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(htmlContent, true)

        mailSender.send(mimeMessage)
    }

}