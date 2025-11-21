package sistema.veterinario.api.veterinario.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistema.veterinario.api.veterinario.model.entity.UsuarioSistema;
import sistema.veterinario.api.veterinario.model.enums.SituacaoEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSistemaDTO {

    private Long id;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String nomeLogin;

    @NotBlank(message = "Email vazio")
    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    private Integer crmv;

    @NotNull
    private String funcao;
    
    @NotNull
    private SituacaoEnum situacao;

    public UsuarioSistemaDTO(UsuarioSistema usuarioSistema) {
        this(
            usuarioSistema.getId(),
            usuarioSistema.getNomeCompleto(),
            usuarioSistema.getNomeLogin(),
            usuarioSistema.getEmail(),
            null,
            usuarioSistema.getCrmv(),
            usuarioSistema.getFuncao().toString(),
            usuarioSistema.getSituacao()
        );
    }

}
