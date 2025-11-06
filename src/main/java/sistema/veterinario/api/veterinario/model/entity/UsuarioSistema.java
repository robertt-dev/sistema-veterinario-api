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
import sistema.veterinario.api.veterinario.model.enums.SituacaoUsuarioEnum;

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
    private int crmv;

    @Enumerated(EnumType.STRING)
    FuncaoUsuarioEnum funcao;

    @Enumerated(EnumType.STRING)
    SituacaoUsuarioEnum situacao;

    public UsuarioSistema(UsuarioSistemaDTO usuario) {
        this.nomeCompleto = usuario.getNomeCompleto();
        this.nomeLogin = usuario.getNomeLogin();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.crmv = usuario.getCrmv();
        this.funcao = usuario.getFuncao();
        this.situacao = usuario.getSituacao();
    }

}
