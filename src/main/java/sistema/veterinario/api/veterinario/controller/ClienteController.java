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

import jakarta.validation.Valid;
import sistema.veterinario.api.veterinario.model.dto.ClienteDTO;
import sistema.veterinario.api.veterinario.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
  
  @Autowired
  private ClienteService clienteService;

  @PostMapping("/cadastrar")
  public ResponseEntity<ClienteDTO> cadastrarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
    clienteService.cadastrarCliente(clienteDTO);

    return ResponseEntity.ok().build();
  }

  @GetMapping("/listar")
  public ResponseEntity<Page<ClienteDTO>> listarCliente(Pageable listar) {
   return ResponseEntity.ok(clienteService.listarCliente(listar));
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<String> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
    clienteService.atualizarCliente(id, clienteDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/deletar/{id}")
  public ResponseEntity<String> deletarCliente(@PathVariable Long id) {
    clienteService.deletarCliente(id);
    return ResponseEntity.ok().build();
  }


}
