package org.aidiary.controller;

import org.aidiary.dto.PersonaCreateRequest;
import org.aidiary.entity.Persona;
import org.aidiary.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    /**
     * 페르소나 생성
     * - 부모 두 명의 성격과 얼굴 이미지를 입력받아 페르소나 생성
     */
    @PostMapping("/create")
    public ResponseEntity<?> createPersona(
            @RequestParam("parentAImage") MultipartFile parentAImage,
            @RequestParam("parentBImage") MultipartFile parentBImage,
            @RequestParam("parentATraits") String parentATraits,
            @RequestParam("parentBTraits") String parentBTraits,
            @RequestParam("name") String name,
            @RequestParam("userId") Long userId
    ) {
        try {
            Persona persona = personaService.createPersona(userId, name, parentATraits, parentBTraits, parentAImage, parentBImage);
            return ResponseEntity.ok(persona);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
