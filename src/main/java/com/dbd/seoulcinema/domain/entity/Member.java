package com.dbd.seoulcinema.domain.entity;

import com.dbd.seoulcinema.domain.enumeration.ClientGrade;
import com.dbd.seoulcinema.global.utils.ClientGradeConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(length = 64, nullable = false)
    private String password;

    @Column(columnDefinition = "CHAR(11)")
    private String phoneNumber;

    @Column(columnDefinition = "NUMBER(10,0)", nullable = false)
    @ColumnDefault("0")
    private Long point;


    @Convert(converter = ClientGradeConverter.class)
    @Column(columnDefinition = "CHAR(6)", nullable = false)
    //clientGrade > clientGradeCode로 변수명 수정함
    private ClientGrade clientGrade;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate birth;

    public void accumulateAndUsePoint(Integer point, Integer totalPrice) {
        this.point -= point;
        this.point += (long) (totalPrice * 0.1);
    }
}
