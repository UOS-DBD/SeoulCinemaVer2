package com.dbd.seoulcinema.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CODE_TYPE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class CodeType {

    @Id
    @Column(columnDefinition = "CHAR(4)")
    private String codeTypeNumber;

    @Column(columnDefinition = "CHAR(4)",nullable = false)
    private String codeTypeName;
}
