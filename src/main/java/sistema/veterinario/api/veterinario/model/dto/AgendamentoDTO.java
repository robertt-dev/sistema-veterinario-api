package sistema.veterinario.api.veterinario.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistema.veterinario.api.veterinario.model.entity.Agendamento;
import sistema.veterinario.api.veterinario.model.entity.Animal;
import sistema.veterinario.api.veterinario.model.entity.UsuarioSistema;
import sistema.veterinario.api.veterinario.model.enums.StatusAgendamentoEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {

    private Long id;

    private LocalDate dataAgendamento;
    private LocalTime horaAgendamento;

    @NotBlank
    private String motivo;

    private StatusAgendamentoEnum statusAgendamentoEnum;

    @NotNull
    private UsuarioSistemaDTO veterinario;

    @NotNull(message = "informe o recepcionista")
    private UsuarioSistemaDTO recepcionista;

    @NotNull
    private AnimalDTO animal;

    public AgendamentoDTO(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getDataAgendamento(),
                agendamento.getHoraAgendamento(),
                agendamento.getMotivo(),
                agendamento.getStatusAgendamentoEnum(),
                new UsuarioSistemaDTO(agendamento.getVeterinario()),
                new UsuarioSistemaDTO(agendamento.getRecepcionista()),
                new AnimalDTO(agendamento.getAnimal())
            );
    }
}
