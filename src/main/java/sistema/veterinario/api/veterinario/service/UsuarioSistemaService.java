package sistema.veterinario.api.veterinario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import sistema.veterinario.api.veterinario.exception.VeterinarioException;
import sistema.veterinario.api.veterinario.model.dto.UsuarioSistemaDTO;
import sistema.veterinario.api.veterinario.model.entity.UsuarioSistema;
import sistema.veterinario.api.veterinario.model.enums.SituacaoUsuarioEnum;
import sistema.veterinario.api.veterinario.model.repository.UsuarioSistemaRepository;

@Service
@Transactional
public class UsuarioSistemaService {

    @Autowired
    private UsuarioSistemaRepository usuarioSistemaRepository;

    public Page<UsuarioSistemaDTO> listarUsuarioSistema(Pageable lista) {
        return usuarioSistemaRepository
        .findAllBySituacao(SituacaoUsuarioEnum.ATIV, lista)
        .map(UsuarioSistemaDTO::new);
    }

    public void cadastroUsuario(@Valid UsuarioSistemaDTO usuario) {

        if (usuarioSistemaRepository.existsByEmail(usuario.getEmail())) {
            throw new VeterinarioException("Este Email já existe!");
        }

        usuarioSistemaRepository.save(new UsuarioSistema(usuario));
    }


    //Revisar sobre
    public void deletarUsuario(Long id) {
        Optional<UsuarioSistema> usuarioOptional = usuarioSistemaRepository.findById(id);
        if(usuarioOptional.isPresent()){
            UsuarioSistema usuario = usuarioOptional.get();
            usuario.setSituacao(SituacaoUsuarioEnum.INAT);
            this.usuarioSistemaRepository.save(usuario);
        } else {
            throw new VeterinarioException("Esse usuario não existe!"); 
        };
    }

}
