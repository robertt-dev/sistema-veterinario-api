package sistema.veterinario.api.veterinario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<UsuarioSistemaDTO> listar() {
        return usuarioSistemaService.listar();
    }

    @PostMapping("/cadastro")
    public void cadastroUsuario(@RequestBody UsuarioSistemaDTO usuario) {
        
        usuarioSistemaService.cadastroUsuario(usuario);
    }
}
