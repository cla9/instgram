package com.instagram.member.service;

import javax.servlet.http.HttpServletRequest;

import static com.instagram.member.model.dto.SignUp.SignUpRequestDTO;
import static com.instagram.member.model.dto.SignUp.SignUpResponseDTO;

public interface MemberService {
    SignUpResponseDTO signUpMember(SignUpRequestDTO requestDTO, HttpServletRequest httpRequest);
}
