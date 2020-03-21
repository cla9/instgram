package com.instagram.member.common.util.api;

import com.instagram.member.model.dto.SignUp.SignUpMailRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "mail", url = "${external-api.mail.url}")
public interface MailService {
    @PostMapping("/email")
    String sendMemberAuthMail(SignUpMailRequestDTO requestDTO);
}
