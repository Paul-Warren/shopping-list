package edu.depaul.cdm.se.shoppinglist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.ISpringTemplateEngine;

import java.util.Map;

@Service
public class EmailTemplateService {

    @Autowired
    private ISpringTemplateEngine templateEngine;

    @Value("${email.templateName}")
    private String templateName;

    public String format(Map<String, Object> valueMap) {
        Context context = new Context();
        context.setVariables(valueMap);
        return templateEngine.process(templateName, context);
    }
}
