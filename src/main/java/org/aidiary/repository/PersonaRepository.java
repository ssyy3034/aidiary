package org.aidiary.repository;

import org.aidiary.entity.Persona;
import org.aidiary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    // 사용자 ID로 페르소나 조회
    Optional<Persona> findByUser(User user);

    // 사용자 ID로 페르소나가 존재하는지 확인
    boolean existsByUser(User user);
}
