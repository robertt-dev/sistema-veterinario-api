package sistema.veterinario.api.veterinario.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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

    public void cadastroUsuario(@Valid UsuarioSistemaDTO usuarioDTO) {

        this.verificacaoCadastro(usuarioDTO);

        usuarioDTO.setSituacao(SituacaoUsuarioEnum.ATIV);
        usuarioSistemaRepository.save(new UsuarioSistema(usuarioDTO));
    }

    public void verificacaoCadastro(UsuarioSistemaDTO usuarioDTO) {

        if (StringUtils.isBlank(usuarioDTO.getNomeCompleto())) {
            throw new VeterinarioException("O campo Nome completo é obrigatorio!");
        }

        if (StringUtils.isBlank(usuarioDTO.getNomeLogin())) {
            throw new VeterinarioException("O campo Login é obrigatorio!");
        }

        if (StringUtils.isBlank(usuarioDTO.getEmail())) {
            throw new VeterinarioException("O campo Email é obrigatorio!");
        }

        if (StringUtils.isBlank(usuarioDTO.getSenha())) {
            throw new VeterinarioException("O campo senha é obrigatorio!");
        }

        if (usuarioDTO.getCrmv() == null) {
            throw new VeterinarioException("O campo crmv é obrigatorio!");
        }

        if (StringUtils.isBlank(usuarioDTO.getNomeCompleto())) {
            throw new VeterinarioException("O campo Email é obrigatorio!");
        }

        if (StringUtils.isBlank(usuarioDTO.getFuncao())) {
            throw new VeterinarioException("O campo Função é obrigatorio!");
        }

        if (usuarioSistemaRepository.existsByNomeLogin(usuarioDTO.getNomeLogin())) {
            throw new VeterinarioException("Nome de Login já existe!");
        }

        if (usuarioSistemaRepository.existsByCrmv(usuarioDTO.getCrmv())) {
            throw new VeterinarioException("CRMV já cadastrado!");
        }

        if (usuarioSistemaRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new VeterinarioException("Este Email já existe!");
        }

    }

    // Revisar sobre
    public void deletarUsuario(Long id) {
        Optional<UsuarioSistema> usuarioOptional = usuarioSistemaRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            UsuarioSistema usuario = usuarioOptional.get();
            usuario.setSituacao(SituacaoUsuarioEnum.INAT);
            this.usuarioSistemaRepository.save(usuario);
        } else {
            throw new VeterinarioException("Esse usuario não existe!");
        }
        ;
    }

    public void atualizarUsuarioSistema(Long id, UsuarioSistemaDTO usuarioSistemaDTO) {
        Optional<UsuarioSistema> usuarioOptionalId = 
        usuarioSistemaRepository.findById(id);
        Optional<UsuarioSistema> usuarioOptionalEmail = 
        usuarioSistemaRepository.findByEmail(usuarioSistemaDTO.getEmail());

        if (usuarioOptionalId.isPresent()) {
             UsuarioSistema usuario = usuarioOptionalId.get();
             usuario.setNomeCompleto(usuarioSistemaDTO.getNomeCompleto());
             usuarioSistemaRepository.save(usuario);
        } 
    }
}
