package sistema.veterinario.api.veterinario.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sistema.veterinario.api.veterinario.exception.VeterinarioException;
import sistema.veterinario.api.veterinario.model.dto.AgendamentoDTO;
import sistema.veterinario.api.veterinario.model.entity.Agendamento;
import sistema.veterinario.api.veterinario.model.enums.StatusAgendamentoEnum;
import sistema.veterinario.api.veterinario.model.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public void agendarConsulta(AgendamentoDTO agendamentoDTO) {

        this.verificarAgendamentoConsulta(agendamentoDTO);
        agendamentoDTO.setStatusAgendamentoEnum(StatusAgendamentoEnum.AGENDADO);

        agendamentoRepository.save(new Agendamento(agendamentoDTO));
    }

    private void verificarAgendamentoConsulta(AgendamentoDTO agendamentoDTO) {
        if (agendamentoDTO.getDataAgendamento() == null) {
            throw new VeterinarioException("É necessário colocar a data do agendamento!");
        }

        if (agendamentoDTO.getHoraAgendamento() == null) {
            throw new VeterinarioException("É necessário o horario do agendamento!");
        }

        if (StringUtils.isBlank(agendamentoDTO.getMotivo())) {
            throw new VeterinarioException("O motivo do agendamento é obrigatório!");
        }

        if (agendamentoDTO.getVeterinario() == null) {
            throw new VeterinarioException("O Veterinario é obrigatorio!");
        }

        if (agendamentoDTO.getAnimal() == null) {
            throw new VeterinarioException("O animal é obrigatorio!");
        }

        if (agendamentoDTO.getRecepcionista() == null) {
            throw new VeterinarioException("Informe o recepcionista!");
        }
    }

    public Page<AgendamentoDTO> listarAgendamentos(Pageable lista) {

        return agendamentoRepository.findAll(lista).map(AgendamentoDTO::new);
    }

}
