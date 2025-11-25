package sistema.veterinario.api.veterinario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistema.veterinario.api.veterinario.model.dto.AgendamentoDTO;
import sistema.veterinario.api.veterinario.service.AgendamentoService;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    
    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping("/agendar")
    public ResponseEntity<String> agendar(@RequestBody AgendamentoDTO agendamentoDTO) {

        agendamentoService.agendarConsulta(agendamentoDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<AgendamentoDTO>> listarAgendamentos(Pageable lista) {

        return ResponseEntity.ok(agendamentoService.listarAgendamentos(lista));
    }
}
