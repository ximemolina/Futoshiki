package modeloFutoshiki;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


/**
 *
 * @author ximena molina - juan pablo cambronero
 */


public class Correo {
    
    private String from;
    private String password;
    private String host;
    private Properties properties;

    /**
     *
     * @param from
     * @param password
     * @param host
     */
    public Correo(String from, String password, String host) {
        this.from = from;
        this.password = password;
        this.host = host;

        // Configuración del servidor de correo con autenticación
        properties = new Properties();
        properties.put("mail.smtp.auth", "true"); // Habilitar autenticación SMTP
        properties.put("mail.smtp.starttls.enable", "true"); // Habilitar StartTLS
        properties.put("mail.smtp.host", host); // Servidor SMTP
        properties.put("mail.smtp.port", "587"); // Puerto SMTP (comúnmente 587 para TLS)
        properties.put("mail.smtp.ssl.trust", host); // Confiar en el host SMTP
    }
    
    public void enviarCorreo(String to, String asunto, String cuerpo) {
        // Crear una sesión con autenticación
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Crear el mensaje de correo
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(asunto);

            // Establecer el contenido del correo (solo texto)
            message.setText(cuerpo);

            // Enviar el mensaje
            Transport.send(message);

        } catch (MessagingException e) {
            System.err.println("Error al enviar el mensaje de correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
