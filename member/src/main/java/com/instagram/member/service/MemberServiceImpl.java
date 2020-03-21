package com.instagram.member.service;

import com.common.command.CreateMember;
import com.instagram.member.common.error.exception.CommandException;
import com.instagram.member.common.util.api.MailService;
import com.instagram.member.model.dto.SignUp.SignUpRequestDTO;
import com.instagram.member.model.dto.SignUp.SignUpResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.instagram.member.model.dto.SignUp.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final CommandGateway commandGateway;
    private final SignUpMailContentBuilder mailContentBuilder;
    private final MailService mailService;

    @Override
    public SignUpResponseDTO signUpMember(SignUpRequestDTO requestDTO, HttpServletRequest httpRequest) {
        log.info("send signup command");
        SignUpResponseDTO result = sendSignUpCommand(requestDTO);
        sendSignUpMail(requestDTO,httpRequest);
        return result;
    }

    private void sendSignUpMail(SignUpRequestDTO requestDTO, HttpServletRequest httpRequest) {
        String mailId = UUID.randomUUID().toString();
        SignUpMailRequestDTO mailRequestDTO = SignUpMailRequestDTO.builder()
                .subject("[instagram] 회원가입 이메일 인증")
                .address(requestDTO.getEmail().getEmailAddress())
                .content(mailContentBuilder.build(mailId, httpRequest)).build();
        String result = mailService.sendMemberAuthMail(mailRequestDTO);
        log.info("result {}", result);
    }

    private SignUpResponseDTO sendSignUpCommand(SignUpRequestDTO requestDTO) {
        String result = sendSinUpCommandAndWaitFor10Sec(requestDTO)
                .orElseThrow(() -> new CommandException("SignUp Command Exception"));

        return SignUpResponseDTO.builder()
                .memberID(result)
                .build();
    }

    private Optional<String> sendSinUpCommandAndWaitFor10Sec(SignUpRequestDTO requestDTO) {
        String userID = UUID.randomUUID().toString();
        return Optional.ofNullable(commandGateway.sendAndWait(createMemberCreationCommand(requestDTO,userID), 10, TimeUnit.SECONDS));
    }

    private CreateMember createMemberCreationCommand(SignUpRequestDTO requestDTO, String userID) {
        return CreateMember.builder()
                    .memberName(requestDTO.getName().getUserName())
                    .memberFullName(requestDTO.getName().getFullName())
                    .memberEmail(requestDTO.getEmail().getEmailAddress())
                    .password(requestDTO.getPassword().getPassword())
                    .memberID(userID)
                .build();
    }

}
