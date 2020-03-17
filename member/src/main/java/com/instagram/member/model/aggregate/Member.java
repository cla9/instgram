package com.instagram.member.model.aggregate;

import com.common.command.CreateMember;
import com.common.event.CreatedMember;
import com.instagram.member.common.util.converter.MemberStatusConverter;
import com.instagram.member.model.Email;
import com.instagram.member.model.Name;
import com.instagram.member.model.Password;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "MEMBER")
@Table(name = "MEMBER_INFO",
        uniqueConstraints = {
                @UniqueConstraint(name = "IX01_MEMBER", columnNames = "member_name"),
                @UniqueConstraint(name = "IX02_MEMBER", columnNames = "email")
        })
@Slf4j
public class Member {
    @AggregateIdentifier
    @Id
    @Column(name = "member_id")
    private String memberID;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userName", column = @Column(name = "member_name", unique = true, nullable = false, length = 30)),
            @AttributeOverride(name = "fullName", column = @Column(name = "member_full_name"))
    })
    private Name name;

    @Embedded
    private Password password;

    @Embedded
    @AttributeOverride(name = "email", column = @Column(name = "email", unique = true, nullable = false))
    private Email email;

    @Column(name = "status")
    @Convert(converter = MemberStatusConverter.class)
    private MemberStatus memberStatus;

    @CommandHandler
    public Member(CreateMember command) {
        log.debug("handling {}", command);
        changePendingStatus();
        //Todo Email 서버 연동 로직 구현 필요
        notifyMemberCreation(command);


    }

    @EventSourcingHandler
    protected void applyCreateMemberEvt(CreatedMember event) {
        log.debug("event {}", event);
        this.memberID = event.getMemberID();
        this.name = Name.builder()
                .userName(event.getMemberName())
                .fullName(event.getMemberFullName())
                .build();
        this.password = Password.of(event.getPassword());
        this.email = Email.of(event.getMemberEmail());
    }

    private void changePendingStatus() {
        this.memberStatus = MemberStatus.PENDING;
    }

    private void notifyMemberCreation(CreateMember command) {
        apply(CreatedMember.builder()
                .memberID(command.getMemberID())
                .memberName(command.getMemberName())
                .password(command.getPassword())
                .memberFullName(command.getMemberFullName())
                .memberEmail(command.getMemberEmail())
                .build());
    }
}
