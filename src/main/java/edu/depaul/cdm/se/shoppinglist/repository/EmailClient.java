package edu.depaul.cdm.se.shoppinglist.repository;

import edu.depaul.cdm.se.shoppinglist.model.EmailRequest;
import edu.depaul.cdm.se.shoppinglist.model.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmailClient {

    @Autowired
    private MailSender mailSender;

    @Value("${email.from}")
    private String from;

    public boolean sendEmail(Optional<ShoppingList> shoppingList, EmailRequest emailRequest) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(emailRequest.getEmailAddress());
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject("Your shopping list!");
        simpleMailMessage.setText("this is an email test!");

        mailSender.send(simpleMailMessage);
        return true;
    }
}
