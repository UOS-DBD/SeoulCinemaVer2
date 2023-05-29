package com.dbd.seoulcinema.domain.entity;

import java.time.LocalDate;
import javax.persistence.*;

import com.dbd.seoulcinema.domain.enumeration.ClientGrade;
import com.dbd.seoulcinema.global.utils.ClientGradeConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Member {

    @Id
    @Column(name = "CLIENT_ID", length = 20)
    private String clientId;

    @Column(name = "PASSWORD", length = 64)
    private String password;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "POINT")
    private Long point;

    @Column(name = "CLIENT_GRADE_CODE")
    @Convert(converter = ClientGradeConverter.class)
    private ClientGrade clientGrade;

    @Column(name = "BIRTH")
    private LocalDate localDate;
}
