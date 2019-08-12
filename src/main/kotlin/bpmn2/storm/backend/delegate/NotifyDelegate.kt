package bpmn2.storm.backend.delegate

import bpmn2.storm.backend.model.BusinessTrip
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.simplejavamail.email.EmailBuilder
import org.simplejavamail.mailer.config.TransportStrategy
import org.simplejavamail.mailer.MailerBuilder
import org.simplejavamail.mailer.Mailer





@Component("notifyDelegate")
class NotifyDelegate: JavaDelegate {
    @Value("\${smtpHost}")
    private var smtpHost: String? = null
    @Value("\${smtpPort}")
    private var smtpPort: Int? = null
    @Value("\${smtpUserName}")
    private var smtpUserName: String? = null
    @Value("\${smtpPassword}")
    private var smtpPassword: String? = null
    @Value("\${smtpFromName}")
    private var smtpFromName: String? = null
    @Value("\${smtpFromEmail}")
    private var smtpFromEmail: String? = null
    override fun execute(execution: DelegateExecution) {
        val application = execution.getVariable("application") as BusinessTrip
        val emailTo = execution.processEngineServices.identityService.createUserQuery().userId(application.employee)?.singleResult()?.email
        val subject = "Your trip from ${application.from} to ${application.to} is ready!"
        var body = "Here you trip details \n" +
                "------ \n" +
                "From: ${application.from} \n" +
                "To:  ${application.to} \n" +
                "Fly description:  ${application.flyDescription} \n" +
                "Hotel description:  ${application.hotelDescription} \n" +
                "------ \n" +
                "Bye!"

        if (emailTo != null) {
            val email = EmailBuilder.startingBlank()
                    .from(smtpFromName, smtpFromEmail!!)
                    .to(emailTo)
                    .withSubject(subject)
                    .withPlainText(body)
                    .buildEmail()


            val mailer = MailerBuilder
                    .withSMTPServer(smtpHost, smtpPort, smtpUserName, smtpPassword)
                    .withTransportStrategy(TransportStrategy.SMTPS)
                    .clearEmailAddressCriteria() // turns off email validation
                    .buildMailer()

            mailer.sendMail(email)
        }
    }
}

