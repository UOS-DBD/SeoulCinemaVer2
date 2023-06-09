package com.dbd.seoulcinema.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SPECIFIC_CODE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class SpecificCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(length = 3)
    private Long specificCodeNumber;

    @Column(length = 6, nullable = false)
    private String specificCodeName;

    @ManyToOne
    @JoinColumn(name = "CODE_TYPE_NUMBER", nullable = false)
    private CodeType codeType;

    @Column(columnDefinition = "CHAR(2)", nullable = false)
    private String code;
}
