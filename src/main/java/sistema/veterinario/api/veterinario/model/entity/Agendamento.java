package sistema.veterinario.api.veterinario.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistema.veterinario.api.veterinario.model.dto.AgendamentoDTO;
import sistema.veterinario.api.veterinario.model.enums.StatusAgendamentoEnum;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataAgendamento;
    private LocalTime horaAgendamento;
    private String motivo;

    @Enumerated(EnumType.STRING)
    StatusAgendamentoEnum statusAgendamentoEnum;

    @ManyToOne
    private UsuarioSistema veterinario;

    @ManyToOne
    private Animal animal;

    public Agendamento(AgendamentoDTO agendamentoDTO) {
        this.dataAgendamento = agendamentoDTO.getDataAgendamento();
        this.horaAgendamento = agendamentoDTO.getHoraAgendamento();
        this.motivo = agendamentoDTO.getMotivo();
        this.statusAgendamentoEnum = agendamentoDTO.getStatusAgendamentoEnum();
        this.veterinario = agendamentoDTO.getVeterinario();
        this.animal = agendamentoDTO.getAnimal();
    }

}
