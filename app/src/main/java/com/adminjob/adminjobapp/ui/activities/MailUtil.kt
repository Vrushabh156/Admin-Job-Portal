package com.adminjob.adminjobapp.ui.activities

import java.util.Properties
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object MailUtil {
    fun sendEmail(recipient: String, subject: String, body: String) {
        val props = Properties()
        props["mail.smtp.host"] = "smtp.gmail.com"
        props["mail.smtp.socketFactory.port"] = "465"
        props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.port"] = "465"

        val session = Session.getDefaultInstance(props,
            object : javax.mail.Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    // Your email and password
                    return PasswordAuthentication("your_email@gmail.com", "your_password")
                }
            })

        try {
            val message = MimeMessage(session)
            message.setFrom(InternetAddress("your_email@gmail.com"))
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient))
            message.subject = subject
            message.setText(body)
            Transport.send(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
