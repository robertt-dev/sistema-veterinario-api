package sistema.veterinario.api.veterinario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sistema.veterinario.api.veterinario.model.dto.UsuarioSistemaDTO;
import sistema.veterinario.api.veterinario.model.entity.UsuarioSistema;
import sistema.veterinario.api.veterinario.model.repository.UsuarioSistemaRepository;

@Service
@Transactional
public class UsuarioSistemaService {

    @Autowired
    private UsuarioSistemaRepository usuarioSistemaRepository;

    public List<UsuarioSistemaDTO> listar() {
        return usuarioSistemaRepository.findAll().stream().map(UsuarioSistemaDTO::new).toList();
    }

    public void cadastroUsuario(UsuarioSistemaDTO usuario) {

        if (usuarioSistemaRepository.existsByEmail(usuario.getNome())) {
            throw new IllegalArgumentException("Este Email já existe!");
        }

        usuarioSistemaRepository.save(new UsuarioSistema(usuario));
    }

}
