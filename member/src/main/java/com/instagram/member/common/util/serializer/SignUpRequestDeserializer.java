package com.instagram.member.common.util.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.instagram.member.model.Email;
import com.instagram.member.model.Name;
import com.instagram.member.model.Password;
import com.instagram.member.model.dto.SignUp.SignUpRequestDTO;

import java.io.IOException;

public class SignUpRequestDeserializer extends JsonDeserializer<SignUpRequestDTO> {

    @Override
    public SignUpRequestDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final ObjectCodec codec = p.getCodec();
        final JsonNode jsonNode =codec.readTree(p);
        return SignUpRequestDTO.builder()
                .name(Name.builder().userName(jsonNode.get("user_name").asText()).fullName(jsonNode.get("full_name").asText()).build())
                .password(Password.of(jsonNode.get("password").asText()))
                .email(Email.of(jsonNode.get("email").asText()))
                .build();
    }
}