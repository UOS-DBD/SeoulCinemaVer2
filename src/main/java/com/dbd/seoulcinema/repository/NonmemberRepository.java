package com.dbd.seoulcinema.repository;

import com.dbd.seoulcinema.domain.entity.NonMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonmemberRepository extends JpaRepository<NonMember, String> {
}
