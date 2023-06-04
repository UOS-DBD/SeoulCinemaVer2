package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.ClientGrade;
import com.dbd.seoulcinema.global.utils.ClientGradeConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Member {

    @Id
    @Column(length = 20)
    private String clientId;

    @Column(length = 64)
    private String password;


    private String phoneNumber;


    private Long point;


    @Convert(converter = ClientGradeConverter.class)
    private ClientGrade clientGrade;


    private LocalDate birth;

    public void accumulateAndUsePoint(Integer point, Integer totalPrice) {
        this.point-=point;
        this.point += (long)(totalPrice * 0.1);
    }
}
