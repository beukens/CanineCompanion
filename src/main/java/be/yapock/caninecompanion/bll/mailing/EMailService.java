package be.yapock.caninecompanion.bll.mailing;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EMailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    private String getHtmlContent(Mail mail){
        Context context = new Context();
        context.setVariables(mail.getTemplate().getProps());
        return springTemplateEngine.process(mail.getTemplate().getTemplate(), context);
    }

    public void sendInvitationEmail(String email, String firstName, String lastName, String token) throws MessagingException{
        //TODO a mettre a jour
        String createLink = "http://8081/create-account?token="+token;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> properties =new HashMap<String, Object>();
        properties.put("firstName", firstName);
        properties.put("lastName",lastName);
        properties.put("link", createLink);

        Mail mail = Mail.builder()
                .to(email)
                .from("guichetplayzone@gmail.com")
                .template(new Mail.MailTemplate("createUser",properties))
                .subject("Demande de création de compte")
                .build();

        String html = getHtmlContent(mail);

        helper.setTo(mail.getTo());
        helper.setFrom(mail.getFrom());
        helper.setSubject(mail.getSubject());
        helper.setText(html, true);
    }
}
