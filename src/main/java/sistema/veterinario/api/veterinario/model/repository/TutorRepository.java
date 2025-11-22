package sistema.veterinario.api.veterinario.model.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sistema.veterinario.api.veterinario.model.entity.Tutor;
import sistema.veterinario.api.veterinario.model.enums.SituacaoEnum;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

  Page<Tutor> findAllBySituacaoEnum(SituacaoEnum SituacaoEnum, Pageable listar);

  boolean existsByCpf(String cpf);
  
}
