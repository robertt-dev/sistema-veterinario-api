package sistema.veterinario.api.veterinario.model.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistema.veterinario.api.veterinario.model.entity.UsuarioSistema;
import sistema.veterinario.api.veterinario.model.enums.SituacaoUsuarioEnum;

@Repository
public interface UsuarioSistemaRepository extends JpaRepository<UsuarioSistema, Long> {
    

    Page<UsuarioSistema> findAllBySituacao(SituacaoUsuarioEnum situacao, Pageable pageable);

    boolean existsByNomeLogin(String nomeLogin);

    boolean existsByCrmv(int crmv);

    boolean existsByEmail(String email);
    
    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByNomeLoginAndIdNot(String nomeLogin, Long id);

    boolean existsByCrmvAndIdNot(Integer crmv, Long id);

    Optional<UsuarioSistema> findByEmail(String email);

    Optional<UsuarioSistema> findByNomeLogin(String nomeLogin);

    Optional<UsuarioSistema> findBySenha(String senha);
}
