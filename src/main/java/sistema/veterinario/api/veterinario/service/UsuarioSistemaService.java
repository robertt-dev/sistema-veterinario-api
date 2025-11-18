package sistema.veterinario.api.veterinario.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import sistema.veterinario.api.veterinario.model.enums.FuncaoUsuarioEnum;
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

    public String transformaSenhaHash(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes());
            byte[] digest = md.digest();
            BigInteger no = new BigInteger(1, digest);
            return String.format("%032x", no);  // 32-character hex string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastroUsuario(@Valid UsuarioSistemaDTO usuarioDTO) {

        this.verificacaoCadastro(usuarioDTO);

        usuarioDTO.setSenha(transformaSenhaHash(usuarioDTO.getSenha()));

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

        if (usuarioSistemaRepository.existsByEmailAndIdNot(usuarioSistemaDTO.getEmail(), id)) {
            throw new VeterinarioException("Já existe um usuario com esse Email!");
        }

        if (usuarioSistemaRepository.existsByNomeLoginAndIdNot(usuarioSistemaDTO.getNomeLogin(), id)) {
            throw new VeterinarioException("Este Login já esta em uso!");
        }

        if (usuarioSistemaRepository.existsByCrmvAndIdNot(usuarioSistemaDTO.getCrmv(), id)) {
            throw new VeterinarioException("Este CRMV já existe!");
        }

        Optional<UsuarioSistema> usuarioOptionalId = usuarioSistemaRepository.findById(id);

        if (usuarioOptionalId.isPresent()) {
            UsuarioSistema usuario = usuarioOptionalId.get();
            usuario.setEmail(usuarioSistemaDTO.getEmail());
            usuario.setNomeLogin(usuarioSistemaDTO.getNomeLogin());
            usuario.setCrmv(usuarioSistemaDTO.getCrmv());
            usuario.setNomeCompleto(usuarioSistemaDTO.getNomeCompleto());
            usuario.setFuncao(FuncaoUsuarioEnum.valueOf(usuarioSistemaDTO.getFuncao()));
            usuario.setSituacao(usuarioSistemaDTO.getSituacao());
            usuario.setSenha(usuarioSistemaDTO.getSenha());
            usuarioSistemaRepository.save(usuario);
        }
    }

    public String autenticacaoUsuarioSistema(UsuarioSistemaDTO usuarioDTO) {
        
        UsuarioSistema usuario = usuarioSistemaRepository
                        .findByNomeLogin(usuarioDTO.getNomeLogin())
                        .orElseThrow(() -> new VeterinarioException("Login ou senha inválidos!"));

        String senhaDigitadaHash = transformaSenhaHash(usuarioDTO.getSenha());

        if(!senhaDigitadaHash.equals(usuario.getSenha())) {
            throw new VeterinarioException("Login ou senha inválidos!");
        } 

        return "Usuário Conectado!";
    }
}
