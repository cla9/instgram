package com.instagram.member.service;

import com.common.command.CreateMember;
import com.instagram.member.common.error.exception.CommandException;
import com.instagram.member.common.error.exception.EmailDuplicationException;
import com.instagram.member.common.error.exception.UserNameDuplicationException;
import com.instagram.member.model.Email;
import com.instagram.member.model.Name;
import com.instagram.member.model.dto.SignUp.SignUpRequestDTO;
import com.instagram.member.model.dto.SignUp.SignUpResponseDTO;
import com.instagram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final CommandGateway commandGateway;
    private final MemberRepository repository;

    @Override
    public SignUpResponseDTO signUpMember(SignUpRequestDTO requestDTO) {
        log.info("send signup command");
        verifyMemberName(requestDTO.getName());
        verifyEmail(requestDTO.getEmail());
        return sendSignUpCommand(requestDTO);
    }

    private void verifyMemberName(Name name){
        boolean isExistsUserName = repository.existsByUserName(name.getUserName());
        if(isExistsUserName) throw new UserNameDuplicationException("사용자 이름이 중복되었습니다." + name.getUserName());
    }

    private void verifyEmail(Email email){
        boolean isExistsEmail = repository.existsByEmail(email);
        if(isExistsEmail) throw new EmailDuplicationException("사용자 이메일이 중복되었습니다. " + email.getEmailAddress());
    }

    private SignUpResponseDTO sendSignUpCommand(SignUpRequestDTO requestDTO) {
        System.out.println(requestDTO);
        Optional<String> result = sendSinUpCommandAndWaitFor10Sec(requestDTO);
        return createSignUpResponseDTO(result);
    }

    private SignUpResponseDTO createSignUpResponseDTO(Optional<String> result) {
        return SignUpResponseDTO.builder()
                .memberID(result.orElseThrow(() -> new CommandException("SignUp Command Exception")))
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
