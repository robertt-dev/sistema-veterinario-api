package sistema.veterinario.api.veterinario.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistema.veterinario.api.veterinario.model.entity.Tutor;
import sistema.veterinario.api.veterinario.model.enums.SituacaoEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorDTO {
  
  private Long id;

  @NotBlank
  private String nome;

  @NotBlank
  @Pattern(regexp = "^(?:\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$")
  private String cpf;

  @NotBlank
  @Pattern(regexp = "\\d{9,11}")
  private String telefone;
  
  @Email
  private String email;

  SituacaoEnum situacaoEnum;

  public TutorDTO(Tutor tutor) {
    this(
      tutor.getId(),
      tutor.getNome(),
      tutor.getCpf(),
      tutor.getTelefone(),
      tutor.getEmail(),
      tutor.getSituacaoEnum()
    );
  }
}
