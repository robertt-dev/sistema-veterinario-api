package sistema.veterinario.api.veterinario.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistema.veterinario.api.veterinario.model.entity.UsuarioSistema;
import sistema.veterinario.api.veterinario.model.enums.SituacaoUsuarioEnum;

@Repository
public interface UsuarioSistemaRepository extends JpaRepository<UsuarioSistema, Long> {
    
    boolean existsByEmail(String email);

    Page<UsuarioSistema> findAllBySituacao(SituacaoUsuarioEnum situacao, Pageable pageable);
}
