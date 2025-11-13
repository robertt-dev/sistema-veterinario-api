package sistema.veterinario.api.veterinario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<UsuarioSistemaDTO> listarUsuarioSistema(Pageable lista) {
        return usuarioSistemaService.listarUsuarioSistema(lista);
    }

    @PostMapping("/cadastro")
    public void cadastroUsuarioSistema(@RequestBody UsuarioSistemaDTO usuario) {
        usuarioSistemaService.cadastroUsuario(usuario); 
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarUsuarioSistema(@PathVariable Long id) {
        usuarioSistemaService.deletarUsuario(id);
    }

    @PutMapping("/atualizar/{id}")
    public void atualizarUsuarioSistema(@PathVariable Long id, @RequestBody UsuarioSistemaDTO usuarioDTO) {
        usuarioSistemaService.atualizarUsuarioSistema(id, usuarioDTO);
    } 

    @PostMapping("/login")
    public void autenticacao(UsuarioSistemaDTO usuarioDTO) {
        usuarioSistemaService.autenticacaoUsuarioSistema();
    }
}
