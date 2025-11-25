package sistema.veterinario.api.veterinario.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sistema.veterinario.api.veterinario.model.entity.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    
}
