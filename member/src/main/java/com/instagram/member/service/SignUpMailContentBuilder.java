package com.instagram.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class SignUpMailContentBuilder {
    private final TemplateEngine engine;

    public String build(String message, HttpServletRequest httpRequest){
        Context context = new Context();
        context.setVariable("uid",message);
        context.setVariable("baseUrl",httpRequest.getRequestURL());
        return engine.process("signupmail", context);
    }
}
