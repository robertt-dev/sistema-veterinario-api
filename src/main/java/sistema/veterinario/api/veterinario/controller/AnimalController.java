package sistema.veterinario.api.veterinario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistema.veterinario.api.veterinario.model.dto.AnimalDTO;
import sistema.veterinario.api.veterinario.service.AnimalService;

@RestController
@RequestMapping("/animal")
public class AnimalController {
  
  @Autowired
  private AnimalService animalService;

  @GetMapping("/listar")
  public ResponseEntity<Page<AnimalDTO>> listarAnimal(Pageable listar) {
    return ResponseEntity.ok(animalService.listarAnimal(listar));
  }

  @PostMapping("/cadastro")
  public ResponseEntity<AnimalDTO> cadastrarAnimal(@RequestBody AnimalDTO animalDTO) {
    animalService.cadastroAnimal(animalDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/deletar/{id}")
  public ResponseEntity<String> deletaAnimal(@PathVariable Long id) {
    animalService.deletaAnimal(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<String> atualizarAnimal(@PathVariable Long id, @RequestBody AnimalDTO animalDTO) {
    animalService.atualizarAnimal(id, animalDTO);
    return ResponseEntity.ok().build();
  }

}
