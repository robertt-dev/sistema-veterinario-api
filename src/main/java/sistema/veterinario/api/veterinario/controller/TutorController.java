package sistema.veterinario.api.veterinario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sistema.veterinario.api.veterinario.model.dto.TutorDTO;
import sistema.veterinario.api.veterinario.service.TutorService;

@RestController
@RequestMapping("/tutor")
public class TutorController {
  
  @Autowired
  private TutorService tutorService;

  @PostMapping("/cadastrar")
  public ResponseEntity<TutorDTO> cadastrarTutor(@Valid @RequestBody TutorDTO tutorDTO) {
    tutorService.cadastrarTutor(tutorDTO);

    return ResponseEntity.ok().build();
  }

  @GetMapping("/listar")
  public ResponseEntity<Page<TutorDTO>> listarTutor(Pageable listar) {
   return ResponseEntity.ok(tutorService.listarTutor(listar));
  }

}
