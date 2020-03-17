package com.common.command;


import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
public class CreateMember {
    @TargetAggregateIdentifier
    private String memberID;
    private String memberName;
    private String password;
    private String memberFullName;
    private String memberEmail;
}
