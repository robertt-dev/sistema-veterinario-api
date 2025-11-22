package sistema.veterinario.api.veterinario.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistema.veterinario.api.veterinario.model.entity.Animal;
import sistema.veterinario.api.veterinario.model.entity.Tutor;
import sistema.veterinario.api.veterinario.model.enums.DeOndeAnimalEnum;
import sistema.veterinario.api.veterinario.model.enums.SituacaoEnum;
import sistema.veterinario.api.veterinario.model.enums.TemperamentoAnimalEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AnimalDTO {
  
  private Long id;

  @NotBlank
  private String nome;

  @NotBlank
  private String especie;

  @NotBlank
  private String raca;

  @NotBlank
  private String corPelo;

  @NotBlank
  private String sexo;

  @NotBlank
  @Pattern(regexp = "\\d{2}")
  private Integer idadeAnimal;

  @NotNull
  private TemperamentoAnimalEnum tempAnimalEnum;

  @NotNull
  private DeOndeAnimalEnum deOndeEnum;

  private SituacaoEnum situacaoEnum;

  @NotNull
  private Tutor tutor;

  public AnimalDTO(Animal animal) {
    this(
      animal.getId(),
      animal.getNome(),
      animal.getEspecie(),
      animal.getRaca(),
      animal.getCorPelo(),
      animal.getSexo(),
      animal.getIdadeAnimal(),
      animal.getTempAnimalEnum(),
      animal.getDeOndeEnum(),
      animal.getSituacaoEnum(),
      animal.getTutor()
    );
  }
}
