package com.instagram.member.controller;

import com.instagram.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.instagram.member.model.dto.SignUp.SignUpRequestDTO;
import static com.instagram.member.model.dto.SignUp.SignUpResponseDTO;

@RestController
@RequestMapping("/api/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signUpMember(@RequestBody @Validated SignUpRequestDTO signUpDTO, HttpServletRequest httpRequest){
        log.info("request signup member");
        return ResponseEntity.ok().body(memberService.signUpMember(signUpDTO,httpRequest));
    }
}
