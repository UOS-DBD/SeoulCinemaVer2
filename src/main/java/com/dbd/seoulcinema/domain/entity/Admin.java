package com.dbd.seoulcinema.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "ADMIN")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Admin {

    @Id
    @Column(name = "ADMIN_ID")
    private String adminId;

    @Column(name = "PASSWORD")
    private String password;

    //TODO(관리자등급)
}
