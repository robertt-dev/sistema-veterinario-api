package sistema.veterinario.api.veterinario.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistema.veterinario.api.veterinario.model.entity.Animal;
import sistema.veterinario.api.veterinario.model.enums.SituacaoEnum;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>{

  Page<Animal> findAllBySituacaoEnum(SituacaoEnum situacaoEnum, Pageable lista);
  
}
