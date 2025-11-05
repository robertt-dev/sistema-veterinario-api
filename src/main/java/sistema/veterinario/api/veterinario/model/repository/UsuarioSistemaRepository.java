package sistema.veterinario.api.veterinario.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistema.veterinario.api.veterinario.model.entity.UsuarioSistema;

@Repository
public interface UsuarioSistemaRepository extends JpaRepository<UsuarioSistema, Long> {
    
    boolean existsByEmail(String email);
}
