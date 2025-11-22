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

import sistema.veterinario.api.veterinario.model.dto.UsuarioSistemaDTO;
import sistema.veterinario.api.veterinario.service.UsuarioSistemaService;

@RestController
@RequestMapping("/usuario")
public class UsuarioSistemaController {

    @Autowired
    private UsuarioSistemaService usuarioSistemaService;

    @GetMapping("/listar")
    public ResponseEntity<Page<UsuarioSistemaDTO>> listarUsuarioSistema(Pageable lista) {
        return ResponseEntity.ok(usuarioSistemaService.listarUsuarioSistema(lista));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioSistemaDTO> cadastroUsuarioSistema(@RequestBody UsuarioSistemaDTO usuario) {
        usuarioSistemaService.cadastroUsuario(usuario);
        return ResponseEntity.ok().build(); 
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarUsuarioSistema(@PathVariable Long id) {
        usuarioSistemaService.deletarUsuario(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarUsuarioSistema(@PathVariable Long id, @RequestBody UsuarioSistemaDTO usuarioDTO) {
        usuarioSistemaService.atualizarUsuarioSistema(id, usuarioDTO);
        return ResponseEntity.ok().build();
    } 

    @PostMapping("/login")
    public ResponseEntity<String> autenticacao(@RequestBody UsuarioSistemaDTO usuarioDTO) {
        String mensagem = usuarioSistemaService.autenticacaoUsuarioSistema(usuarioDTO);
        return ResponseEntity.ok(mensagem);
    }
}
