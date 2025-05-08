package org.aidiary.service;

import org.aidiary.entity.Persona;
import org.aidiary.entity.User;
import org.aidiary.repository.PersonaRepository;
import org.aidiary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PersonaService {

    private final PersonaRepository personaRepository;
    private final UserRepository userRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository, UserRepository userRepository) {
        this.personaRepository = personaRepository;
        this.userRepository = userRepository;
    }

    /**
     * 페르소나 보유 여부 체크
     */
    public Optional<Persona> checkPersona(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        return personaRepository.findByUser(user);
    }

    /**
     * 페르소나 생성
     */
    public Persona createPersona(Long userId, String name) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        if (personaRepository.existsByUser(user)) {
            throw new IllegalStateException("이미 페르소나가 존재합니다.");
        }

        Persona persona = Persona.builder()
                .user(user)
                .name(name)
                .traits("[]") // 초기 성격은 비어있는 JSON 배열로 저장
                .build();

        return personaRepository.save(persona);
    }
}
