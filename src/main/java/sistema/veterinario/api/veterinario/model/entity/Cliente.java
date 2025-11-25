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
import sistema.veterinario.api.veterinario.model.dto.ClienteDTO;
import sistema.veterinario.api.veterinario.model.enums.SituacaoEnum;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String cpf;
  private String telefone;
  private String email;

  @Enumerated(EnumType.STRING)
  SituacaoEnum situacaoEnum;

  public Cliente(ClienteDTO clienteDTO) {
    this(
        clienteDTO.getId(),
        clienteDTO.getNome(),
        clienteDTO.getCpf(),
        clienteDTO.getTelefone(),
        clienteDTO.getEmail(),
        clienteDTO.getSituacaoEnum());
  }
}
