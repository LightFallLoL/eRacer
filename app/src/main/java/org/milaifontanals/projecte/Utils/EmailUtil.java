package org.milaifontanals.projecte.Utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Classe que volia utilitzar per enviar mails pero em diu que requereixo d'un domini.
 */
public class EmailUtil {
    public static void enviarEmail(String correu, String cursa, String circuit, int idInscrit) {
        final String username = "eracernoreply@gmail.com";
        final String password = "47122330E";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(correu));
            message.setSubject("Confirmació de participació a la Cursa: " + cursa + " al Circuit: " + circuit);
            message.setText("Vostè té el número d'inscripció número " + idInscrit + ", que és únic.\nLi desitgem molta sort a la cursa!");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
