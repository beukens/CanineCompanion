package be.yapock.caninecompanion.bll.mailing;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

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
}
