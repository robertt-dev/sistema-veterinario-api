package sistema.veterinario.api.veterinario.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistema.veterinario.api.veterinario.model.entity.UsuarioSistema;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSistemaDTO {

    private Long id;

    private String nome;
    private String email;
    private String senha;

    public UsuarioSistemaDTO(UsuarioSistema usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                null);
    }

}
