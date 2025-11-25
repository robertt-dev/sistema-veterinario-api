package sistema.veterinario.api.veterinario.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistema.veterinario.api.veterinario.model.dto.UsuarioSistemaDTO;
import sistema.veterinario.api.veterinario.model.enums.FuncaoUsuarioEnum;
import sistema.veterinario.api.veterinario.model.enums.SituacaoEnum;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;
    private String nomeLogin;
    private String email;
    private String senha;
    private String crmv;

    @Enumerated(EnumType.STRING)
    FuncaoUsuarioEnum funcaoEnum;

    @Enumerated(EnumType.STRING)
    SituacaoEnum situacaoEnum;

    public UsuarioSistema(UsuarioSistemaDTO usuarioDTO) {
        this.nomeCompleto = usuarioDTO.getNomeCompleto();
        this.nomeLogin = usuarioDTO.getNomeLogin();
        this.email = usuarioDTO.getEmail();
        this.senha = usuarioDTO.getSenha();
        this.crmv = usuarioDTO.getCrmv();
        this.funcaoEnum = FuncaoUsuarioEnum.valueOf(usuarioDTO.getFuncaoEnum());
        this.situacaoEnum = usuarioDTO.getSituacaoEnum();
    }

    public UsuarioSistema(Long id) {
        this.id = id;
    }

}
