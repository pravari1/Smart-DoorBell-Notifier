package hello;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// Send a simple, single part, text/plain e-mail
public class SendEmail {

	public static void sendEmail(String to, String from, String subject, String message) throws AddressException {

		String recipientList = to;

		final String user = "patelprakash841@gmail.com";
		final String password = "Patel@333";

		// need to specify which host to send it to
		String host = "smtp.gmail.com";
		int port = 465;
		InternetAddress[] addresses = InternetAddress.parse(recipientList);

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		props.put("mail.smtp.user", user);
		props.put("mail.smtp.password", password);
		// If using static Transport.send(),
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			Transport transport = session.getTransport("smtp");
			transport.connect(host, user, password);
			// session.setDebug(true);
			MimeMessage msg = new MimeMessage(session);
			msg.setRecipients(Message.RecipientType.TO, addresses);
			msg.setFrom(new InternetAddress(from));
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(message);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}// End of class
