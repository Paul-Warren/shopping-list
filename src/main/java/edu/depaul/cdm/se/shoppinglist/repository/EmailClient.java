package edu.depaul.cdm.se.shoppinglist.repository;

import edu.depaul.cdm.se.shoppinglist.model.EmailRequest;
import edu.depaul.cdm.se.shoppinglist.model.ShoppingList;
import edu.depaul.cdm.se.shoppinglist.service.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmailClient {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Value("${email.from}")
    private String from;

    public boolean sendEmail(ShoppingList shoppingList, EmailRequest emailRequest) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(emailRequest.getEmailAddress());
            helper.setSubject("Your shopping list!");
            helper.setText(emailTemplateService.format(buildValueMap(shoppingList)), true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private Map<String, Object> buildValueMap(ShoppingList shoppingList) {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("shoppingList", shoppingList);
        return valueMap;
    }
}
